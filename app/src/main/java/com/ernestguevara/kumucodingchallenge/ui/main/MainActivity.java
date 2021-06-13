package com.ernestguevara.kumucodingchallenge.ui.main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ernestguevara.kumucodingchallenge.BaseActivity;
import com.ernestguevara.kumucodingchallenge.R;
import com.ernestguevara.kumucodingchallenge.di.viewmodel.ViewModelProviderFactory;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.GsonBuilder;


import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import static com.ernestguevara.kumucodingchallenge.util.Constants.DB_Fragment;
import static com.ernestguevara.kumucodingchallenge.util.Constants.ID_Fragment;
import static com.ernestguevara.kumucodingchallenge.util.Constants.ITEM_ID;

/**
 * MainActivity
 * In it's layout in R.layout.activity fragment is added for navigation jetpack
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Inject
    ViewModelProviderFactory providerFactory;

    private MainViewModel viewModel;
    public NavController navController;
    public NavHostFragment navHostFragment;

    private AppBarConfiguration appBarConfiguration;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Attaches MainViewModel to this activity
        viewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel.class);

        toolbar = findViewById(R.id.main_toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);
        appBarLayout = findViewById(R.id.app_bar_layout);

        //setup custom toolbar
        setupToolbar(R.id.main_toolbar);

        //api call for location
        getLocation();
    }

    /**
     * Setup the navigation jetpack
     */
    private void setupNavigation() {
        //set toolbar visible after all is loaded
        toolbar.setVisibility(View.VISIBLE);

        appBarConfiguration = new AppBarConfiguration.Builder(R.id.dashboardFragment, R.id.itemDetailFragment).build();
        navController = Navigation.findNavController(this, R.id.main_host_fragment);
        navController.setGraph(R.navigation.main_nav);

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_host_fragment);
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
    }

    private void getLocation() {
        //check first if connected to the network
        if (isNetworkConnected()) {
            //api call to get the location data which provides country code
            viewModel.getLocationData().observe(this, locationDataResource -> {
                switch (locationDataResource.status) {
                    case LOADING:
                        break;
                    case SUCCESS:
//                        Log.d(TAG, "getLocation: success " + new GsonBuilder().create().toJson(locationDataResource));
                        setupNavigation();
                        break;

                    case CLIENT_ERROR:
                    case SERVER_ERROR:
                        viewModel.createDefaultUserProfile("PH");
                        setupNavigation();
//                        Log.d(TAG, "getLocation: fail " + new GsonBuilder().create().toJson(locationDataResource));
                        break;
                }
            });
        } else {
            //if not connected, create user which country code is in Philippines
            viewModel.createDefaultUserProfile("PH");
            //setup navigation
            setupNavigation();
            Toast.makeText(this, getString(R.string.no_internet),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void navigateTo(int resId) {
        navController.navigate(resId);
    }

    @Override
    public void navigateTo(int resId, Bundle bundle) {
        navController.navigate(resId, bundle);
    }

    @Override
    public void setToolbarTitle(String title) {
        toolbarTitle.setText(title);
    }

    @Override
    public void resetToolbar() {
        appBarLayout.setExpanded(true, false);
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}