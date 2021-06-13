package com.ernestguevara.kumucodingchallenge.ui.item;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProviders;

import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ernestguevara.kumucodingchallenge.BaseFragment;
import com.ernestguevara.kumucodingchallenge.R;
import com.ernestguevara.kumucodingchallenge.db.entity.LastFragment;
import com.ernestguevara.kumucodingchallenge.di.viewmodel.ViewModelProviderFactory;
import com.ernestguevara.kumucodingchallenge.network.retrofit.Model.SearchResult;
import com.ernestguevara.kumucodingchallenge.ui.main.MainViewModel;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import static com.ernestguevara.kumucodingchallenge.util.Constants.DB_Fragment;
import static com.ernestguevara.kumucodingchallenge.util.Constants.ID_Fragment;
import static com.ernestguevara.kumucodingchallenge.util.Constants.ITEM_BUNDLE;

public class ItemDetailFragment extends BaseFragment {

    private static final String TAG = "ItemDetailFragment";

    @Inject
    ViewModelProviderFactory providerFactory;

    private MainViewModel viewModel;

    private SearchResult searchResult;

    private ImageView itemImg;
    private TextView itemTitle;
    private TextView itemArtist;
    private TextView itemLongDesc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //gets the arguments that's passed in this fragment
        if (getArguments() != null) {
            searchResult = getArguments().getParcelable(ITEM_BUNDLE);
        }

        //Attaches MainViewModel to this fragment
        viewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel.class);

        //enable menu options
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);
        itemImg = view.findViewById(R.id.item_image);
        itemTitle = view.findViewById(R.id.item_title);
        itemArtist = view.findViewById(R.id.item_artist);
        itemLongDesc = view.findViewById(R.id.item_long_desc);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //add data to the views
        populateViews();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.item_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    /**
     * method that adds data to the views
     */
    private void populateViews() {
        Glide.with(this)
                .load(searchResult.getArtworkUrl100())
                .apply(new RequestOptions().dontTransform().placeholder(R.mipmap.ic_launcher))
                .into(itemImg);
        itemTitle.setText(searchResult.getTrackName());
        itemArtist.setText(searchResult.getArtistName());
        itemLongDesc.setText(searchResult.getLongDescription());
        itemLongDesc.setMovementMethod(new ScrollingMovementMethod());
        setToolbarTitle(getString(R.string.item_price, String.valueOf(searchResult.getTrackPrice())));
    }

    @Override
    public void onResume() {
        super.onResume();
        //reset the collapsing toolbar
        resetToolbar();
    }

    @Override
    public void onStop() {
        super.onStop();
        //adds the dashboard as the last fragment
        LastFragment lastFragment = new LastFragment();
        lastFragment.setFragmentName(ID_Fragment);
        lastFragment.setItem_id(searchResult.getResultId());
        viewModel.addFragmentEntry(lastFragment);
    }
}