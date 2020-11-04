package com.seyfettin.vitrinovaapplication.databinding;

import androidx.databinding.BindingAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.seyfettin.vitrinovaapplication.data.remote.Resource;
import com.seyfettin.vitrinovaapplication.view.base.BasePagerAdapter;

import java.util.List;

final class PagerBindingAdapter {
    private PagerBindingAdapter() {
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = "resource")
    public static void setResource(ViewPager viewPager, Resource resource){
        PagerAdapter adapter = viewPager.getAdapter();
        if(adapter == null)
            return;

        if(resource == null || resource.data == null)
            return;

        if(adapter instanceof BasePagerAdapter){
            ((BasePagerAdapter)adapter).setData((List) resource.data);
        }
    }
}
