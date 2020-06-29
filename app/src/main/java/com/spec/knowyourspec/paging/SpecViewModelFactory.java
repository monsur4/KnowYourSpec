package com.spec.knowyourspec.paging;

import android.app.Activity;
import android.app.Application;
import android.os.Build;

import com.spec.knowyourspec.data.Repository;

import java.lang.reflect.InvocationTargetException;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SpecViewModelFactory implements ViewModelProvider.Factory {
    Repository mRepository;

    public SpecViewModelFactory (Activity activity){
        Application application = activity.getApplication();
        mRepository = Repository.getInstance(application);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try{
            return modelClass.getConstructor(Repository.class).newInstance(mRepository);
        }catch (InstantiationException | IllegalAccessException |
                NoSuchMethodException | InvocationTargetException e){
            throw new RuntimeException("Cannot instantiate SpecViewModel", e);
        }
    }
}
