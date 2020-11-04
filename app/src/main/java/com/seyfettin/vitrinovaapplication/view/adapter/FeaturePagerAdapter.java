package com.seyfettin.vitrinovaapplication.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.seyfettin.vitrinovaapplication.data.remote.model.Feature;
import com.seyfettin.vitrinovaapplication.databinding.ItemFeatureBinding;
import com.seyfettin.vitrinovaapplication.view.base.BasePagerAdapter;

import java.util.List;

public class FeaturePagerAdapter extends BasePagerAdapter<Feature> {
    private List<Feature> data;
    private final LayoutInflater layoutInflater;

    public FeaturePagerAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void setData(List<Feature> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ItemFeatureBinding binding = ItemFeatureBinding.inflate(layoutInflater,container,false);
        binding.setFeature(data.get(position));
        binding.executePendingBindings();
        container.addView(binding.getRoot());
        return binding.getRoot();
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
