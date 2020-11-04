package com.seyfettin.vitrinovaapplication.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.seyfettin.vitrinovaapplication.di.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

import com.seyfettin.vitrinovaapplication.viewmodel.DetailViewModel;
import com.seyfettin.vitrinovaapplication.viewmodel.DiscoverListViewModel;
import com.seyfettin.vitrinovaapplication.viewmodel.ViewModelFactory;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DiscoverListViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsDiscoverListViewModel(DiscoverListViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsDetailViewModel(DetailViewModel viewModel);

    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
