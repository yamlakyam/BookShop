package com.example.bookshop.Api;

import com.example.bookshop.Models.AllData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @GET("volumes?q=flowers&startIndex=0&maxResults=10")
    Call<AllData> getBookItems();

    @GET("/&startIndex=0")
    Call<AllData> getBooks(@QueryMap Map<String, String> params);
}
