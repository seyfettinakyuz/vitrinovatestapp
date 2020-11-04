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
import com.seyfettin.vitrinovaapplication.data.remote.model.new_product.Product;
import com.seyfettin.vitrinovaapplication.databinding.ItemProductBinding;
import com.seyfettin.vitrinovaapplication.view.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;


public class NewProductAllListAdapter extends BaseAdapter<NewProductAllListAdapter.ViewHolder, Product> {

    private List<Product> data;
    private int lastPosition = -1;


    public NewProductAllListAdapter() {
        data = new ArrayList<>();
    }

    @Override
    public void setData(List<Product> data) {
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
            ItemProductBinding itemBinding = ItemProductBinding.inflate(inflater, parent, false);
            return new ViewHolder(itemBinding);
        }

        final ItemProductBinding binding;

        private ViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void onBind(Product product) {
            binding.setProduct(product);
            binding.executePendingBindings();
        }
    }
}