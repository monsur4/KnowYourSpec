package com.spec.knowyourspec.answer;

import com.spec.knowyourspec.data.Repository;
import com.spec.knowyourspec.data.Spec;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnswersViewModel extends ViewModel {
    private Repository mRepository;
    private final MutableLiveData<List<Spec>> mSpecList = new MutableLiveData<>();

    public AnswersViewModel(Repository repository){
        mRepository = repository;
    }

    public MutableLiveData<List<Spec>> getSpecList(){
        return mSpecList;
    }

    public void beginGame(){
        List<Spec> specList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            specList.add(new Spec("firstName" + i+1, "lastName" + i+1,
                    "alias" + i+1, "email" + i+1, "bestSpec" + i+1,
                    "favoriteLecturer" + i+1, "alternateCareer" + i+1,
                    "likelyCareer" + i+1, "favoriteQuote" + i+1));
        }
        mSpecList.setValue(specList);
    }
}
