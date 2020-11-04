package com.seyfettin.vitrinovaapplication.di.components;


import android.app.Application;

import com.seyfettin.vitrinovaapplication.BaseApplication;
import com.seyfettin.vitrinovaapplication.di.builder.ActivityBuilderModule;
import com.seyfettin.vitrinovaapplication.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(BaseApplication baseApplication);
}