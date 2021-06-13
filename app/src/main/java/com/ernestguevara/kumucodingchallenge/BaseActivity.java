package com.ernestguevara.kumucodingchallenge;

import com.ernestguevara.kumucodingchallenge.ui.main.MainActivityListener;

import androidx.appcompat.widget.Toolbar;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * The base activity of the app, this is used when using multiple activities
 * Activity extends BaseActivity and you'll have access to the methods for the whole activity which extends to this
 */
public abstract class BaseActivity extends DaggerAppCompatActivity implements MainActivityListener {

    public Toolbar toolbar;

    public void setupToolbar(int id) {
        toolbar = findViewById(id);
        toolbar.setNavigationIcon(null);
        toolbar.setTitle(null);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }

        toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }
}
