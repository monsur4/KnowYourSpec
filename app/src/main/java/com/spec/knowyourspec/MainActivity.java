package com.spec.knowyourspec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.spec.knowyourspec.answer.AnswersView;
import com.spec.knowyourspec.answer.AnswersViewModel;
import com.spec.knowyourspec.answer.AnswersViewModelFactory;
import com.spec.knowyourspec.data.Spec;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView mSpecImage;
    AnswersView mAnswersView;
    AnswersViewModelFactory mAnswersViewModelFactory;
    AnswersViewModel mAnswersViewModel;
    TextView mSpecName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSpecImage = findViewById(R.id.spec_image_view);
        mSpecImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnswersViewModel.beginGame();
            }
        });
        mAnswersView = findViewById(R.id.answers_view);
        mSpecName = findViewById(R.id.spec_text_view);

        mAnswersViewModelFactory = new AnswersViewModelFactory(this);

        //mAnswersViewModel = ViewModelProvider.of(this).get(AnswersViewModel())
        //mAnswersViewModel = new AnswersViewModel()
        mAnswersViewModel = new ViewModelProvider(this, mAnswersViewModelFactory).get(AnswersViewModel.class);
        mAnswersViewModel.getSpecList().observe(this, new Observer<List<Spec>>() {
            @Override
            public void onChanged(List<Spec> specList) {
                mAnswersView.loadAnswers(specList);
            }
        });

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.open_nav_drawer, R.string.close_nav_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        /*this method loads the default preference values, if it needs to be used somewhere in the activity*/
        //PreferenceManager.setDefaultValues(this, R.xml.pref_settings, false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean showName = sharedPreferences.getBoolean(getString(R.string.pref_key_show_spec_name), false);
        if(showName){
            mSpecName.setVisibility(View.VISIBLE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(getString(R.string.pref_key_notification_chat), true);
            editor.apply(); // commit saves changes immediately while apply() does it in the background
        }else {
            mSpecName.setVisibility(View.GONE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(getString(R.string.pref_key_notification_chat), false);
            editor.apply(); // commit saves changes immediately while apply() does it in the background
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_all_specs:
                Intent listIntent = new Intent(MainActivity.this, SpecListActivity.class);
                startActivity(listIntent);
                return true;
            case R.id.action_settings:
                Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return true;
    }
}
