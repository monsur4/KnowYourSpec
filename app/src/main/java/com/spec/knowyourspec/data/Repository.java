package com.spec.knowyourspec.data;

import android.app.Application;
import android.content.Context;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

public class Repository {
    private SpecDao mDao;
    private static volatile Repository sInstance = null;

    public static Repository getInstance(Application application) {
        if (sInstance == null) {
            synchronized (Repository.class) {
                if (sInstance == null) {
                    SpecRoomDatabase specRoomDatabase = SpecRoomDatabase.getInstance(application);
                    sInstance = new Repository(specRoomDatabase);
                }
            }
        }
        return sInstance;
    }

    private Repository(SpecRoomDatabase specRoomDatabase) {
        mDao = specRoomDatabase.mSpecDao();
    }

    public void getSpec(int id) {
        mDao.getSpec(id);
    }

    public DataSource.Factory<Integer, Spec> getAllSpec() {
        return mDao.getAllSpec();
    }

    public LiveData<List<Spec>> getSomeSpecs(int limit) {
        return mDao.getSomeSpecs(limit);
    }
}
