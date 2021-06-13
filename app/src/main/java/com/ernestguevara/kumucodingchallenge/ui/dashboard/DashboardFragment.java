package com.ernestguevara.kumucodingchallenge.ui.dashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ernestguevara.kumucodingchallenge.BaseFragment;
import com.ernestguevara.kumucodingchallenge.R;
import com.ernestguevara.kumucodingchallenge.db.entity.LastFragment;
import com.ernestguevara.kumucodingchallenge.db.entity.UserProfile;
import com.ernestguevara.kumucodingchallenge.di.viewmodel.ViewModelProviderFactory;
import com.ernestguevara.kumucodingchallenge.network.retrofit.Model.SearchResult;
import com.ernestguevara.kumucodingchallenge.ui.main.MainViewModel;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.ernestguevara.kumucodingchallenge.util.Constants.DB_Fragment;
import static com.ernestguevara.kumucodingchallenge.util.Constants.ID_Fragment;
import static com.ernestguevara.kumucodingchallenge.util.Constants.ITEM_BUNDLE;
import static com.ernestguevara.kumucodingchallenge.util.Constants.ITEM_ID;

public class DashboardFragment extends BaseFragment implements ResultsListAdapter.RecyclerViewClickListener {

    private static final String TAG = "DashboardFragment";

    @Inject
    ViewModelProviderFactory providerFactory;

    private MainViewModel viewModel;

    private ArrayList<SearchResult> mSearchResultList;
    private ResultsListAdapter resultsListAdapter;
    private LinearLayoutManager linearLayoutManager;

    private RecyclerView resultsRv;
    private TextView resultText;
    private TextView lastVisitText;
    private ProgressBar progressBar;

    private UserProfile userProfile;

    private boolean runOnce;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Attaches MainViewModel to this fragment
        viewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel.class);

        //enable menu options
        setHasOptionsMenu(true);

        //boolean for previous session
        runOnce = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        resultsRv = view.findViewById(R.id.db_results_rv);
        resultText = view.findViewById(R.id.db_search_empty);
        progressBar = view.findViewById(R.id.db_progressbar);
        lastVisitText = view.findViewById(R.id.db_last_visit);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSearchResultList = new ArrayList<>();
        //call the db for the date for last visit
        getDate();

        //create recylerview
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        resultsListAdapter = new ResultsListAdapter(getActivity(), this);
        setAdapter();

        //checker for previous session
        if (!runOnce) {
            runOnce = true;
            viewModel.getLastFragment().observe(getViewLifecycleOwner(), lastFragment -> {
                if (lastFragment != null) {
                    if (lastFragment.getFragmentName().equals(ID_Fragment)) {
                        viewModel.getCachedResults().observe(getViewLifecycleOwner(), searchResults -> {
                            for (SearchResult s :
                                    searchResults) {
                                if (s.getResultId() == lastFragment.getItem_id()) {
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable(ITEM_BUNDLE, s);
                                    navigateTo(R.id.action_dashboardFragment_to_itemDetailFragment, bundle);
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        //inflates the search button
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint(getString(R.string.explore_music));

        //listens to the query on the searchview
        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //query itunes api
                queryMusic(query, "all");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)) {
                    //hidden due to the limit of 20 request per minute
//                    queryMusic(newText, "all");
                }
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void getDate() {
        viewModel.getUserProfile().observe(getViewLifecycleOwner(), userProfile -> {
            this.userProfile = new UserProfile();
            this.userProfile = userProfile;
            lastVisitText.setText(getString(R.string.last_visit_date, userProfile.getLastVisit()));
        });
    }

    private void setAdapter() {
        //set the adapter for the recycler view
        resultsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        resultsRv.setHasFixedSize(true);
        resultsRv.setItemAnimator(new DefaultItemAnimator());
        resultsRv.setAdapter(resultsListAdapter);
    }

    /**
     * Query music from the db
     */
    private void queryMusic(String term, String media) {
        //show progress bar while loading
        progressBar.setVisibility(View.VISIBLE);
        //checks if there's an internet
        if (isNetworkConnected()) {
            //Query to itunes api
            viewModel.getSearchData(term, userProfile.getCountryCode(), media, userProfile.isEnableExplicit() ? "Yes" : "No").observe(getViewLifecycleOwner(), baseResponseResource -> {
                switch (baseResponseResource.status) {
                    case LOADING:
                        break;
                    case SUCCESS:
                        //Get the data from the db
                        getDataFromDb();
                        progressBar.setVisibility(View.GONE);
                        break;

                    case CLIENT_ERROR:
                    case SERVER_ERROR:
                        resultsRv.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                        resultText.setVisibility(View.VISIBLE);
//                        Log.d(TAG, "getDashboard: fail" + new GsonBuilder().create().toJson(baseResponseResource));
                        break;
                }
            });
        } else {
            //Loads the last data from the db
            getDataFromDb();
            Toast.makeText(getActivity(), getString(R.string.no_internet),Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Get's the searchlist data from the db
     */
    private void getDataFromDb() {
        viewModel.getCachedResults().observe(getViewLifecycleOwner(), searchResults -> {
//            Log.d(TAG, "queryMusic: " + new GsonBuilder().create().toJson(searchResults));

            //Checks if results is empty, will show the empty message if it do else, clears the list and adds the new one
            if (searchResults.isEmpty()) {
                resultsRv.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                resultText.setVisibility(View.VISIBLE);
            } else {
                resultsRv.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                resultText.setVisibility(View.GONE);
                mSearchResultList.clear();
                mSearchResultList.addAll(searchResults);
                resultsListAdapter.setData(mSearchResultList);
//                Log.d(TAG, "setData: mSearchResultList: "+ new GsonBuilder().create().toJson(mSearchResultList));
            }
        });
    }

    @Override
    public void onClick(View v, int position, List<SearchResult> mSearchResultList) {
        //on click navigate to item list fragment
        Bundle bundle = new Bundle();
        bundle.putParcelable(ITEM_BUNDLE, mSearchResultList.get(position));
        navigateTo(R.id.action_dashboardFragment_to_itemDetailFragment, bundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        //get data from db upon resume
        getDataFromDb();
        setToolbarTitle(getString(R.string.app_name));
        //reset the collapsing toolbar
        resetToolbar();
    }

    @Override
    public void onStop() {
        super.onStop();
        //adds the dashboard as the last fragment
        LastFragment lastFragment = new LastFragment();
        lastFragment.setFragmentName(DB_Fragment);
        lastFragment.setItem_id(0);
        viewModel.addFragmentEntry(lastFragment);
    }
}