package com.example.mymovie.service;

import com.example.mymovie.network.model.request.LoginRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RelayService {

    @GET("app/getMember.dp")
    Call<LoginRequest> test_api_get(@Query("mem_id") String mem_id, @Query("mem_pw") String mem_pw);
}
