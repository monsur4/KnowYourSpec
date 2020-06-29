package com.spec.knowyourspec.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface SpecDao {
    @Insert
    void insert(Spec spec);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Spec... specs);

//    @Delete
//    void delete();

    @Query("SELECT * FROM " + DatabaseConstants.TABLE_NAME + " WHERE " + DatabaseConstants.ID + " =:id")
    Spec getSpec(int id);

    @Query("SELECT * FROM " + DatabaseConstants.TABLE_NAME)
    DataSource.Factory<Integer, Spec> getAllSpec();

    @Query("SELECT * FROM " + DatabaseConstants.TABLE_NAME + " ORDER BY RANDOM() LIMIT :limit")
    LiveData<List<Spec>> getSomeSpecs(int limit);
}
