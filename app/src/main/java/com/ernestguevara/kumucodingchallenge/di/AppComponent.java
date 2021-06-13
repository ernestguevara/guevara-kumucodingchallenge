package com.ernestguevara.kumucodingchallenge.di;

import android.app.Application;


import com.ernestguevara.kumucodingchallenge.BaseApplication;
import com.ernestguevara.kumucodingchallenge.di.module.DatabaseModule;
import com.ernestguevara.kumucodingchallenge.di.module.NetworkModule;
import com.ernestguevara.kumucodingchallenge.di.viewmodel.ViewModelFactoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


/**
 * Simplifies Dependency Inection
 * This extends to the baseapplication which makes the code generation further
 * Also you need to add all your modules in the component
 * @Singleton scope means the appcomponent owned all the singleton, same as scoping when you do a subcomponents using @scope
 * So when destoryed, all their depedency is also destroyed
 */

@Singleton
@Component (modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuildersModule.class,
        AppModule.class,
        ViewModelFactoryModule.class,
        NetworkModule.class,
        DatabaseModule.class
})

// appComponent lives in the Application class to share its lifecycle
public interface AppComponent extends AndroidInjector<BaseApplication> {

        // Reference to the application graph that is used across the whole app
        @Component.Builder
        interface Builder {
            @BindsInstance
            Builder application (Application application);

            AppComponent build();
        }
}
