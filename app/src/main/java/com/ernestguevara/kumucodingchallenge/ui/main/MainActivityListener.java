package com.ernestguevara.kumucodingchallenge.ui.main;

import android.os.Bundle;

/**
 * listener for the mainactivity
 */
public interface MainActivityListener {
    void navigateTo(int resId);
    void navigateTo(int resId, Bundle bundle);
    void setToolbarTitle(String title);
    void resetToolbar();
    boolean isNetworkConnected();
}
