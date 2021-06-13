package com.ernestguevara.kumucodingchallenge;

import android.os.Bundle;

import com.ernestguevara.kumucodingchallenge.ui.main.MainActivityListener;

import dagger.android.support.DaggerFragment;

/**
 * The base activity of the app, this is used when using multiple activities
 * Fragments extends BaseFragment and you'll have access to the methods for the listener of all fragments
 * Also this is extended to the DaggerFragment for the FragmentInjection
 */

public class BaseFragment extends DaggerFragment implements MainActivityListener {

    @Override
    public void navigateTo(int resId) {
        if (getActivity() instanceof MainActivityListener) {
            ((MainActivityListener) getActivity()).navigateTo(resId);
        }
    }

    @Override
    public void navigateTo(int resId, Bundle bundle) {
        if (getActivity() instanceof MainActivityListener) {
            ((MainActivityListener) getActivity()).navigateTo(resId, bundle);
        }
    }

    @Override
    public void setToolbarTitle(String title) {
        if (getActivity() instanceof MainActivityListener) {
            ((MainActivityListener) getActivity()).setToolbarTitle(title);
        }
    }

    @Override
    public void resetToolbar() {
        if (getActivity() instanceof MainActivityListener) {
            ((MainActivityListener) getActivity()).resetToolbar();
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if (getActivity() instanceof MainActivityListener) {
            return ((MainActivityListener) getActivity()).isNetworkConnected();
        }
        return false;
    }
}
