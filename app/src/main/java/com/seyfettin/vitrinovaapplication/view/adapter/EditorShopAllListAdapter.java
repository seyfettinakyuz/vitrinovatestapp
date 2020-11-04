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
import com.seyfettin.vitrinovaapplication.data.remote.model.EditorShop;
import com.seyfettin.vitrinovaapplication.databinding.ItemAllEditorShopBinding;
import com.seyfettin.vitrinovaapplication.view.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;


public class EditorShopAllListAdapter extends BaseAdapter<EditorShopAllListAdapter.ViewHolder, EditorShop> {

    private List<EditorShop> data;
    private int lastPosition = -1;


    public EditorShopAllListAdapter() {
        data = new ArrayList<>();
    }

    public EditorShop getItem(int position){
        return data.get(position);
    }

    @Override
    public void setData(List<EditorShop> data) {
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
            ItemAllEditorShopBinding itemBinding = ItemAllEditorShopBinding.inflate(inflater, parent, false);
            return new ViewHolder(itemBinding);
        }

        final ItemAllEditorShopBinding binding;

        private ViewHolder(ItemAllEditorShopBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void onBind(EditorShop editorShop) {
            binding.setShop(editorShop);
            binding.executePendingBindings();
        }
    }
}