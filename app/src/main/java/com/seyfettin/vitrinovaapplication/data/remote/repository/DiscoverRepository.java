package com.seyfettin.vitrinovaapplication.data.remote.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.seyfettin.vitrinovaapplication.data.remote.ApiService;
import com.seyfettin.vitrinovaapplication.data.remote.NetworkBoundResource;
import com.seyfettin.vitrinovaapplication.data.remote.Resource;
import com.seyfettin.vitrinovaapplication.data.remote.model.BaseDiscoverObject;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class DiscoverRepository {
    private final ApiService apiService;

    @Inject
    DiscoverRepository(ApiService service) {
        this.apiService = service;
    }

    public LiveData<Resource<List<BaseDiscoverObject>>> getDiscover() {
        return new NetworkBoundResource<List<BaseDiscoverObject>>() {
            @NonNull
            @Override
            protected Call<List<BaseDiscoverObject>> createCall() {
                return apiService.getDiscover();
            }
        }.getAsLiveData();
    }
}

