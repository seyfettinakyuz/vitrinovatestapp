package com.seyfettin.vitrinovaapplication.data.remote;


import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.seyfettin.vitrinovaapplication.R;
import com.seyfettin.vitrinovaapplication.helper.ResourceManager;
import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public abstract class NetworkBoundResource<T> {

    private final MediatorLiveData<Resource<T>> result = new MediatorLiveData<>();

    @MainThread
    protected NetworkBoundResource() {
        result.setValue(Resource.loading(null));
        fetchFromNetwork();
    }

    /**
     * This method fetches the data from remoted service
     */
    private void fetchFromNetwork() {
        createCall().enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                result.setValue(Resource.success(response.body()));
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                result.setValue(Resource.error(getCustomErrorMessage(t),null));
            }
        });
    }

    private String getCustomErrorMessage(Throwable error){

        if (error instanceof SocketTimeoutException) {
            return ResourceManager.getString(R.string.requestTimeOutError);
        } else if (error instanceof MalformedJsonException) {
            return  ResourceManager.getString(R.string.responseMalformedJson);
        } else if (error instanceof IOException) {
            return  ResourceManager.getString(R.string.networkError);
        } else if (error instanceof HttpException) {
            if(((HttpException) error).response() != null){
                return Objects.requireNonNull(((HttpException) error).response()).message();
            }else {
                return ResourceManager.getString(R.string.networkError);
            }
        } else {
            return ResourceManager.getString(R.string.unknownError);
        }

    }

    @NonNull
    @MainThread
    protected abstract Call<T> createCall();

    public final LiveData<Resource<T>> getAsLiveData() {
        return result;
    }
}
