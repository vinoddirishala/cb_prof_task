package com.chotabeta.ptask.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.chotabeta.ptask.beans.Data;
import com.chotabeta.ptask.interfaces.API;
import com.chotabeta.ptask.utils.RetrofitBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vinod Dirishala on 20-02-2021 12:11
 **/
public class DataRepo {


    private String LOG_TAG = DataRepo.class.getSimpleName();
    ArrayList<Data.DataBean> dataBeans;

    public MutableLiveData<ArrayList<Data.DataBean>> getData(){
        dataBeans = new ArrayList<>();
        dataBeans.clear();
        final  MutableLiveData<ArrayList<Data.DataBean>> liveData = new MutableLiveData<ArrayList<Data.DataBean>>();
        API api = RetrofitBuilder.getInstance();
        api.getData().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e(LOG_TAG, "getData response="+response );
                if (response.isSuccessful() && response.body()!=null ) {
                    Log.e(LOG_TAG, "getData response inside="+response.body());
                   // add the response to live data to update the UI
                    Data dataModel = new Gson().fromJson(response.body(), Data.class);
                    Log.d("getData response.size:",dataModel.getRows().size()+" is the size");
                    dataBeans.addAll(dataModel.getRows());
                    liveData.setValue(dataBeans);
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(LOG_TAG, "getData onFailure" + call.toString());
            }
        });
        return liveData;

    }
}
