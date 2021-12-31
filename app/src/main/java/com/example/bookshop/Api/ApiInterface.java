package com.example.bookshop.Api;

import com.example.bookshop.Models.AllData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @GET("volumes?q=flowers&startIndex=0&maxResults=10")
    Call<AllData> getInitialBookItems();

    @GET("volumes?q=flowers")
    Call<AllData> getNextBookItems(
            @Query("startIndex") int startIndex);
}
