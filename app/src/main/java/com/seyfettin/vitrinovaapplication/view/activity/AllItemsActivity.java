package com.seyfettin.vitrinovaapplication.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seyfettin.vitrinovaapplication.R;
import com.seyfettin.vitrinovaapplication.data.remote.ApiConstants;
import com.seyfettin.vitrinovaapplication.data.remote.model.Collection;
import com.seyfettin.vitrinovaapplication.data.remote.model.EditorShop;
import com.seyfettin.vitrinovaapplication.data.remote.model.NewShop;
import com.seyfettin.vitrinovaapplication.data.remote.model.new_product.Product;
import com.seyfettin.vitrinovaapplication.databinding.ActivityAllItemsBinding;
import com.seyfettin.vitrinovaapplication.view.adapter.CollectionAllListAdapter;
import com.seyfettin.vitrinovaapplication.view.adapter.EditorShopAllListAdapter;
import com.seyfettin.vitrinovaapplication.view.adapter.NewProductAllListAdapter;
import com.seyfettin.vitrinovaapplication.view.adapter.NewShopAllListAdapter;
import com.seyfettin.vitrinovaapplication.view.base.BaseActivity;
import com.seyfettin.vitrinovaapplication.viewmodel.DetailViewModel;

import java.util.ArrayList;

import butterknife.BindView;

@SuppressLint("NonConstantResourceId")
public class AllItemsActivity extends BaseActivity<DetailViewModel, ActivityAllItemsBinding> {

    @BindView(R.id.detail_list)
    RecyclerView detailList;

    @Override
    protected Class<DetailViewModel> getViewModel() {
        return DetailViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_all_items;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String type = getIntent().getExtras().getString("type");
        String title = getIntent().getExtras().getString("title");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (type.contentEquals(ApiConstants.NEW_PRODUCTS)) {
            GridLayoutManager layoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
            NewProductAllListAdapter detailListAdapter = new NewProductAllListAdapter();
            ArrayList<Product> data = getIntent().getExtras().getParcelableArrayList("data");
            detailListAdapter.setData(data);
            detailList.setLayoutManager(layoutManager);
            detailList.setAdapter(detailListAdapter);
        } else if (type.contentEquals(ApiConstants.COLLECTIONS)) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            CollectionAllListAdapter detailListAdapter = new CollectionAllListAdapter();
            ArrayList<Collection> data = getIntent().getExtras().getParcelableArrayList("data");
            detailListAdapter.setData(data);
            detailList.setLayoutManager(layoutManager);
            detailList.setAdapter(detailListAdapter);
        } else if (type.contentEquals(ApiConstants.EDITOR_SHOPS)) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            EditorShopAllListAdapter detailListAdapter = new EditorShopAllListAdapter();
            ArrayList<EditorShop> data = getIntent().getExtras().getParcelableArrayList("data");
            detailListAdapter.setData(data);
            detailList.setLayoutManager(layoutManager);
            detailList.setAdapter(detailListAdapter);
        } else if (type.contentEquals(ApiConstants.NEW_SHOPS)) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            NewShopAllListAdapter detailListAdapter = new NewShopAllListAdapter();
            ArrayList<NewShop> data = getIntent().getExtras().getParcelableArrayList("data");
            detailListAdapter.setData(data);
            detailList.setLayoutManager(layoutManager);
            detailList.setAdapter(detailListAdapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        else
        {
            return super.onOptionsItemSelected(item);
        }
    }
}