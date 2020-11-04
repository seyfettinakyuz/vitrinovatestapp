package com.seyfettin.vitrinovaapplication.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.seyfettin.vitrinovaapplication.R;
import com.seyfettin.vitrinovaapplication.data.remote.ApiConstants;
import com.seyfettin.vitrinovaapplication.data.remote.Resource;
import com.seyfettin.vitrinovaapplication.data.remote.Status;
import com.seyfettin.vitrinovaapplication.data.remote.model.BaseDiscoverObject;
import com.seyfettin.vitrinovaapplication.data.remote.model.Category;
import com.seyfettin.vitrinovaapplication.data.remote.model.Collection;
import com.seyfettin.vitrinovaapplication.data.remote.model.EditorShop;
import com.seyfettin.vitrinovaapplication.data.remote.model.Feature;
import com.seyfettin.vitrinovaapplication.data.remote.model.NewShop;
import com.seyfettin.vitrinovaapplication.data.remote.model.new_product.Product;
import com.seyfettin.vitrinovaapplication.databinding.ActivityMainBinding;
import com.seyfettin.vitrinovaapplication.helper.AppSession;
import com.seyfettin.vitrinovaapplication.helper.DepthPageTransformer;
import com.seyfettin.vitrinovaapplication.helper.DisplayUtils;
import com.seyfettin.vitrinovaapplication.helper.ImageHelper;
import com.seyfettin.vitrinovaapplication.helper.ObjectConverter;
import com.seyfettin.vitrinovaapplication.model.MainDiscover;
import com.seyfettin.vitrinovaapplication.view.adapter.CategoryListAdapter;
import com.seyfettin.vitrinovaapplication.view.adapter.CollectionListAdapter;
import com.seyfettin.vitrinovaapplication.view.adapter.EditorShopListAdapter;
import com.seyfettin.vitrinovaapplication.view.adapter.FeaturePagerAdapter;
import com.seyfettin.vitrinovaapplication.view.adapter.NewProductListAdapter;
import com.seyfettin.vitrinovaapplication.view.adapter.NewShopListAdapter;
import com.seyfettin.vitrinovaapplication.view.base.BaseActivity;
import com.seyfettin.vitrinovaapplication.viewmodel.DiscoverListViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@SuppressLint("NonConstantResourceId")
public class MainActivity extends BaseActivity<DiscoverListViewModel, ActivityMainBinding> {
    @BindView(R.id.loading_progress)
    ProgressBar loadingProgress;
    @BindView(R.id.scrool_view)
    NestedScrollView scroolView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.features_viewpager)
    ViewPager featuresViewpager;

    @BindView(R.id.features_tab)
    TabLayout featuresTab;

    @BindView(R.id.new_product_list)
    RecyclerView newProductList;

    @BindView(R.id.category_list)
    RecyclerView categoryList;

    @BindView(R.id.collection_list)
    RecyclerView collectionList;

    @BindView(R.id.editor_list)
    RecyclerView editorList;

    @BindView(R.id.new_shop_list)
    RecyclerView newShopList;

    @BindView(R.id.editor_background)
    ImageView editorBackground;

    @BindView(R.id.search_bar)
    SearchView searchBar;


    MutableLiveData<Bitmap> editorCover = new MutableLiveData<>();
    MainDiscover mainDiscover;

    private static final int RECOGNIZER_RESULT = 1;


    @Override
    protected Class<DiscoverListViewModel> getViewModel() {
        return DiscoverListViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        featuresViewpager.setAdapter(new FeaturePagerAdapter(this));
        featuresViewpager.setPageTransformer(true, new DepthPageTransformer());
        featuresTab.setupWithViewPager(featuresViewpager);

        newProductList.setAdapter(new NewProductListAdapter());
        categoryList.setAdapter(new CategoryListAdapter());
        collectionList.setAdapter(new CollectionListAdapter());
        editorList.setAdapter(new EditorShopListAdapter());
        newShopList.setAdapter(new NewShopListAdapter());

        editorCover.observe(this, bitmap -> {
            editorBackground.setImageBitmap(bitmap);
        });

        loadData();

        swipeRefresh.setOnRefreshListener(() -> {
            if (swipeRefresh.isRefreshing()) {
                swipeRefresh.setRefreshing(false);
            }
            loadingProgress.post(() -> loadingProgress.setVisibility(View.VISIBLE));
            swipeRefresh.post(() ->swipeRefresh.setVisibility(View.GONE));
            loadData();
        });


    }

    @SuppressLint("StaticFieldLeak")
    private void getEditorCoverImage(int position, String url) {
        if (AppSession.editorShopCovers != null &&
                AppSession.editorShopCovers.length > position &&
                AppSession.editorShopCovers[position] != null) {
            editorCover.setValue(AppSession.editorShopCovers[position]);
        } else {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    Bitmap bitmap = ImageHelper.getBitmapFromURL(url);
                    if (bitmap != null) {

                        Bitmap grayScale = new ImageHelper(bitmap)
                                .setResize(DisplayUtils.screenWidth(), DisplayUtils.dpToPx(325))
//                                .setResize(editorBackground.getMeasuredWidth(), editorBackground.getMeasuredHeight())
                                .setGrayScale()
                                .get();
                        AppSession.editorShopCovers[position] = grayScale;
                        editorCover.postValue(grayScale);
                    }
                    return null;
                }
            }.execute();

        }

    }


    @OnClick(R.id.new_product_all)
    void clickNewProductAll() {
        Intent intent = new Intent(this, AllItemsActivity.class);
        intent.putExtra("data", (ArrayList<Product>) mainDiscover.getNewProducts().data);
        intent.putExtra("type", ApiConstants.NEW_PRODUCTS);
        intent.putExtra("title", mainDiscover.getNewProductsTitle());
        startActivity(intent);
    }

    @OnClick(R.id.collection_all)
    void clickCollectionAll() {
        Intent intent = new Intent(this, AllItemsActivity.class);
        intent.putExtra("data", (ArrayList<Collection>) mainDiscover.getCollections().data);
        intent.putExtra("type", ApiConstants.COLLECTIONS);
        intent.putExtra("title", mainDiscover.getCollectionsTitle());
        startActivity(intent);
    }

    @OnClick(R.id.editor_all)
    void clickEditorAll() {
        Intent intent = new Intent(this, AllItemsActivity.class);
        intent.putExtra("data", (ArrayList<EditorShop>) mainDiscover.getEditorShops().data);
        intent.putExtra("type", ApiConstants.EDITOR_SHOPS);
        intent.putExtra("title", mainDiscover.getEditorShopsTitle());
        startActivity(intent);
    }

    @OnClick(R.id.new_shop_all)
    void clickNewShopAll() {
        Intent intent = new Intent(this, AllItemsActivity.class);
        intent.putExtra("data", (ArrayList<NewShop>) mainDiscover.getNewShops().data);
        intent.putExtra("type", ApiConstants.NEW_SHOPS);
        intent.putExtra("title", mainDiscover.getNewShopsTitle());
        startActivity(intent);
    }

    @OnClick(R.id.mic)
    void clickSpeech() {
        Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech to text");
        startActivityForResult(speechIntent, RECOGNIZER_RESULT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RECOGNIZER_RESULT && resultCode == RESULT_OK) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            searchBar.setQuery(result.get(0), false);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void loadData() {
        viewModel.getDiscover().observe(this, discoverResponseResource -> {
            AppSession.clear();
            if (null != discoverResponseResource && discoverResponseResource.data != null
                    || discoverResponseResource.status == Status.SUCCESS) {
                loadingProgress.post(() -> loadingProgress.setVisibility(View.GONE));
                swipeRefresh.post(() ->swipeRefresh.setVisibility(View.VISIBLE));

                mainDiscover = new MainDiscover();

                for (BaseDiscoverObject discoverObject : discoverResponseResource.data) {
                    if (discoverObject.getType().contentEquals(ApiConstants.FEATURED)) {
                        List<Feature> list = ObjectConverter.convertList(discoverObject.getResponse(), Feature.class);
                        mainDiscover.setFeatures(Resource.success(list));
                    } else if (discoverObject.getType().contentEquals(ApiConstants.NEW_PRODUCTS)) {
                        mainDiscover.setNewProductsTitle(discoverObject.getTitle());
                        List<Product> list = ObjectConverter.convertList(discoverObject.getResponse(), Product.class);
                        mainDiscover.setNewProducts(Resource.success(list));
                    } else if (discoverObject.getType().contentEquals(ApiConstants.CATEGORIES)) {
                        mainDiscover.setCategoriesTitle(discoverObject.getTitle());
                        List<Category> list = ObjectConverter.convertList(discoverObject.getResponse(), Category.class);
                        mainDiscover.setCategories(Resource.success(list));
                    } else if (discoverObject.getType().contentEquals(ApiConstants.COLLECTIONS)) {
                        mainDiscover.setCollectionsTitle(discoverObject.getTitle());
                        List<Collection> list = ObjectConverter.convertList(discoverObject.getResponse(), Collection.class);
                        mainDiscover.setCollections(Resource.success(list));
                    } else if (discoverObject.getType().contentEquals(ApiConstants.EDITOR_SHOPS)) {
                        mainDiscover.setEditorShopsTitle(discoverObject.getTitle());
                        List<EditorShop> list = ObjectConverter.convertList(discoverObject.getResponse(), EditorShop.class);
                        getEditorCoverImage(0, list.get(0).getCover().getUrl());
                        mainDiscover.setEditorShops(Resource.success(list));
                        if (AppSession.editorShopCovers == null) {
                            AppSession.editorShopCovers = new Bitmap[list.size()];
                        }
                    } else if (discoverObject.getType().contentEquals(ApiConstants.NEW_SHOPS)) {
                        mainDiscover.setNewShopsTitle(discoverObject.getTitle());
                        List<NewShop> list = ObjectConverter.convertList(discoverObject.getResponse(), NewShop.class);
                        mainDiscover.setNewShops(Resource.success(list));
                    }
                }


                editorList.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                            int position = ((LinearLayoutManager) editorList.getLayoutManager())
                                    .findFirstCompletelyVisibleItemPosition();
                            if (position != -1) {
                                String url = ((EditorShopListAdapter) editorList.getAdapter())
                                        .getItem(position).getCover().getUrl();
                                getEditorCoverImage(position, url);
                            }
                        }
                    }
                });
                dataBinding.setDiscover(mainDiscover);
            }
        });


    }
}