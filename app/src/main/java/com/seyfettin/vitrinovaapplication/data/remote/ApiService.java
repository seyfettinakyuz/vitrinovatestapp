package com.seyfettin.vitrinovaapplication.data.remote;

import com.seyfettin.vitrinovaapplication.data.remote.model.BaseDiscoverObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
//    @GET("discover")
//    Call<DiscoverResponse> getDiscover();

    @GET("discover")
    Call<List<BaseDiscoverObject>> getDiscover();
}
