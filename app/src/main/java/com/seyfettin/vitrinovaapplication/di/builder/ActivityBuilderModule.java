package com.seyfettin.vitrinovaapplication.di.builder;


import com.seyfettin.vitrinovaapplication.view.activity.AllItemsActivity;
import com.seyfettin.vitrinovaapplication.view.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract AllItemsActivity detailActivity();
}