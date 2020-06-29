package com.spec.knowyourspec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.spec.knowyourspec.data.Spec;
import com.spec.knowyourspec.paging.SpecListAdapter;
import com.spec.knowyourspec.paging.SpecViewModel;
import com.spec.knowyourspec.paging.SpecViewModelFactory;

public class SpecListActivity extends AppCompatActivity {
    SpecViewModelFactory mSpecViewModelFactory;
    SpecViewModel mSpecViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spec_list);

        mSpecViewModelFactory = new SpecViewModelFactory(this);
        mSpecViewModel = new ViewModelProvider(this, mSpecViewModelFactory).get(SpecViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.spec_list_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        final SpecListAdapter adapter = new SpecListAdapter();

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        mSpecViewModel.getAllSpecs().observe(this, new Observer<PagedList<Spec>>() {
            @Override
            public void onChanged(PagedList<Spec> specs) {
                adapter.submitList(specs);
            }
        });

    }
}
