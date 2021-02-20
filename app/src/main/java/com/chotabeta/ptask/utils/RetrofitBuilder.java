package com.chotabeta.ptask.utils;

import com.chotabeta.ptask.interfaces.API;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.chotabeta.ptask.utils.Constants.BASE_URL;

/**
 * Created by Vinod Dirishala on 20-02-2021 12:05
 **/

public class RetrofitBuilder {

    private static API api;

    public static synchronized API getInstance(){
        if (api == null) {
            api = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOKHttpClient())
                    .build().create(API.class);
        }
        return api;
    }

    // read and write time out purpose
    private static OkHttpClient getOKHttpClient(){
        return new OkHttpClient().newBuilder()
                // .addInterceptor(new OkHttpInterceptor())
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS).build();
    }

}
