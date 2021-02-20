package com.chotabeta.ptask.interfaces;

import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Vinod Dirishala on 20-02-2021 12:05
 **/
public interface API {

    @GET("facts.json")
    Call<JsonObject> getData();

}
