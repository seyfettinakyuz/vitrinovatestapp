package com.seyfettin.vitrinovaapplication.view.base;

import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public abstract class BasePagerAdapter<D> extends PagerAdapter {
    public abstract void setData(List<D> data);
}
