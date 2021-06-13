package com.ernestguevara.kumucodingchallenge.di.viewmodel;

import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * module classes
 * provides depedency, which later will be injected
 * this provide to the viewmodel factory
 */
@Module
public abstract class ViewModelFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory modelProviderFactory);

    @Provides
    static ViewModelProvider.Factory bindFactory(ViewModelProviderFactory factory) {
        return factory;
    }
}
