package com.chotabeta.ptask.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.chotabeta.ptask.R;
import com.chotabeta.ptask.beans.Data;
import com.chotabeta.ptask.ui.adapters.DataAdapter;
import com.chotabeta.ptask.viewmodels.DataViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainScreen extends AppCompatActivity {

    @BindView(R.id.homeScreenData) RecyclerView homeScreenData;
    Unbinder unbinder;

    DataViewModel dataViewModel = new DataViewModel();
    DataAdapter dataAdapter;
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        unbinder = ButterKnife.bind(this);
        homeScreenData.setHasFixedSize(true);
        homeScreenData.setLayoutManager(linearLayoutManager);
        homeScreenData.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        dataAdapter = new DataAdapter(this,new ArrayList<Data.DataBean>());
        homeScreenData.setAdapter(dataAdapter);
        dataViewModel.getDataFromRepo().observe(this, new Observer<ArrayList<Data.DataBean>>() {
            @Override
            public void onChanged(ArrayList<Data.DataBean> data) {
                // notify the adapter to update the UI on event occurred
                Log.d("getData onChanged:",data.size()+"");
                dataAdapter.addData(data);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}