package com.spec.knowyourspec.data;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Spec.class}, version = 1, exportSchema = false)
public abstract class SpecRoomDatabase extends RoomDatabase {
    private static SpecRoomDatabase sSpecRoomDatabase;

    public abstract SpecDao mSpecDao();

    public static SpecRoomDatabase getInstance(final Context context){
        if (sSpecRoomDatabase == null){
            synchronized(SpecRoomDatabase.class){
                sSpecRoomDatabase = Room.databaseBuilder(context, SpecRoomDatabase.class,
                        DatabaseConstants.SPEC_DATABASE)
                        .fallbackToDestructiveMigration()
                        //.addCallback(sCallback)
                        .addCallback(new RoomDatabase.Callback(){
                            @Override
                            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                super.onOpen(db);
                                Executors.newSingleThreadExecutor().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        populateDemoData(context);
                                    }
                                });
                            }
                        })
                        .build();
            }
        }
        return sSpecRoomDatabase;
    }

    // using this method fails to inability to get the context correctly,
    // which is needed to get the database instance and hence the dao
    // So, normally we'd use the callback declaration with an async task
    // or in this case, by pass the need for the context
    // and rather pass the database instance directly as a parameter so we can get the dao from there
    // TODO: the problem I see here is that, the sSpecRoomDatabase might still be null while we pass
    //  it to populateDemoData(). But I did something similar in firepay that worked fine
    private static RoomDatabase.Callback sCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // TODO: Write code to populate database with about 10 specs
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    populateDemoData(sSpecRoomDatabase);
                }
            });
        }
    };

    // if we require the context, we'd have to create the callback inline as we instantiate the roomdatabase
    private static void populateDemoData(Context context){
        List<Spec> specList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            specList.add(new Spec("firstName" + i+1, "lastName" + i+1,
                    "alias" + i+1, "email" + i+1, "bestSpec" + i+1,
                    "favoriteLecturer" + i+1, "alternateCareer" + i+1,
                    "likelyCareer" + i+1, "favoriteQuote" + i+1));
        }
        SpecDao specDao = SpecRoomDatabase.getInstance(context).mSpecDao();
        specDao.insertAll(specList.get(0), specList.get(1), specList.get(2), specList.get(3),
                specList.get(4), specList.get(5), specList.get(6), specList.get(7), specList.get(8), specList.get(9));
    }

    // but if we use this method, we could create the callback as a static variable and pass it as argument to addCallback
    private static void populateDemoData(SpecRoomDatabase specRoomDatabase){
        List<Spec> specList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            specList.add(new Spec("firstName" + i+1, "lastName" + i+1,
                    "alias" + i+1, "email" + i+1, "bestSpec" + i+1,
                    "favoriteLecturer" + i+1, "alternateCareer" + i+1,
                    "likelyCareer" + i+1, "favoriteQuote" + i+1));
        }
        SpecDao specDao = specRoomDatabase.mSpecDao();
        // TODO: inserting into the database using the insertAll method
        specDao.insertAll(specList.get(0), specList.get(1), specList.get(2), specList.get(3),
                specList.get(4), specList.get(5), specList.get(6), specList.get(7), specList.get(8), specList.get(9));
    }
}
