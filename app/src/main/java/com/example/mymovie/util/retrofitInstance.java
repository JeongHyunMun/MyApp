package com.example.mymovie.util;

import com.example.mymovie.BuildConfig;
import com.example.mymovie.service.RelayService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitInstance {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static RelayService getApiService(){
        return getInstance().create(RelayService.class);
    }

    private static Retrofit getInstance() {
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BUILD_TYPE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }


}
