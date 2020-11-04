package com.seyfettin.vitrinovaapplication.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.seyfettin.vitrinovaapplication.BaseApplication;
import com.seyfettin.vitrinovaapplication.R;
import com.seyfettin.vitrinovaapplication.data.remote.model.NewShop;
import com.seyfettin.vitrinovaapplication.databinding.ItemAllNewShopBinding;
import com.seyfettin.vitrinovaapplication.view.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;


public class NewShopAllListAdapter extends BaseAdapter<NewShopAllListAdapter.ViewHolder, NewShop> {

    private List<NewShop> data;
    private int lastPosition = -1;


    public NewShopAllListAdapter() {
        data = new ArrayList<>();
    }

    public NewShop getItem(int position){
        return data.get(position);
    }

    @Override
    public void setData(List<NewShop> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return ViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.onBind(data.get(position));
        setAnimation(viewHolder.binding.getRoot(),position);
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(BaseApplication.getAppContext(),
                    R.anim.item_anim);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private static ViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            ItemAllNewShopBinding itemBinding = ItemAllNewShopBinding.inflate(inflater, parent, false);
            return new ViewHolder(itemBinding);
        }

        final ItemAllNewShopBinding binding;

        private ViewHolder(ItemAllNewShopBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void onBind(NewShop editorShop) {
            binding.setShop(editorShop);
            binding.executePendingBindings();
        }
    }
}