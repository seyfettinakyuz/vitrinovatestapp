package com.seyfettin.vitrinovaapplication.view.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * File Description
 */
public abstract class BaseActivity<VM extends ViewModel, VDB extends ViewDataBinding> extends AppCompatActivity implements HasSupportFragmentInjector {
    private Unbinder unbinder;
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    protected VM viewModel;

    protected VDB dataBinding;

    protected abstract Class<VM> getViewModel();

    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, getLayoutRes());
        unbinder = ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel());
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentAndroidInjector;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder != null){
            unbinder.unbind();
            unbinder = null;
        }
    }
}