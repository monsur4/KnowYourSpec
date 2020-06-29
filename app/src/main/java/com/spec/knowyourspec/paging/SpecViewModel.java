package com.spec.knowyourspec.paging;

import android.app.Activity;
import android.app.Application;

import com.spec.knowyourspec.data.Repository;
import com.spec.knowyourspec.data.Spec;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class SpecViewModel extends ViewModel {
    private Repository mRepository;
    private LiveData<PagedList<Spec>> mAllSpecs;

    public SpecViewModel(Repository repository){
        mRepository = repository;
        mAllSpecs = new LivePagedListBuilder<>(mRepository.getAllSpec(), 30).build();
    }

    public LiveData<PagedList<Spec>> getAllSpecs(){
        return mAllSpecs;
    }
}
