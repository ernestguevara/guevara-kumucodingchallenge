package com.ernestguevara.kumucodingchallenge.di.main;

import com.ernestguevara.kumucodingchallenge.ui.dashboard.DashboardFragment;
import com.ernestguevara.kumucodingchallenge.ui.item.ItemDetailFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * FragmentBuilder
 * Injects fragments with dagger
 */

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract DashboardFragment contributesDashboardFragment();

    @ContributesAndroidInjector()
    abstract ItemDetailFragment contributesItemDetailFragment();
}
