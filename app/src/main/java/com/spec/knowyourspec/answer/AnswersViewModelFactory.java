package com.spec.knowyourspec.answer;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.spec.knowyourspec.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AnswersViewModelFactory implements ViewModelProvider.Factory {
    Repository  repository;
    public AnswersViewModelFactory(Activity activity){
        Application application = activity.getApplication();
        if(application == null){
            throw new IllegalStateException("Not yet attached to application.");
        }
        repository = Repository.getInstance(application);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(AnswersViewModel.class)){
            return (T) new AnswersViewModel(repository);
        } else{
            throw new RuntimeException("Cannot instantiate AnswersViewModel");
        }

    }
}
