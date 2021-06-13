package com.ernestguevara.kumucodingchallenge.di.main;

import com.ernestguevara.kumucodingchallenge.di.viewmodel.ViewModelKey;
import com.ernestguevara.kumucodingchallenge.ui.main.MainViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Mainviewmodel module
 * binds all view model on main
 */
@Module
public abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindMainViewModel(MainViewModel viewModel);
}
