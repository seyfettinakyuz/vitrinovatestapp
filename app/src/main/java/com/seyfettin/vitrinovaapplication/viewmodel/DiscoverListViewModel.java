package com.seyfettin.vitrinovaapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.seyfettin.vitrinovaapplication.data.remote.Resource;
import com.seyfettin.vitrinovaapplication.data.remote.model.BaseDiscoverObject;
import com.seyfettin.vitrinovaapplication.data.remote.repository.DiscoverRepository;

import java.util.List;

import javax.inject.Inject;

public class DiscoverListViewModel extends ViewModel {
    private final DiscoverRepository discoverRepository;

    @Inject
    public DiscoverListViewModel(DiscoverRepository discoverRepository) {
        this.discoverRepository = discoverRepository;
    }

    public LiveData<Resource<List<BaseDiscoverObject>>> getDiscover() {
        return discoverRepository.getDiscover();
    }

}
