package com.example.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.bookshop.Api.ApiInterface;
import com.example.bookshop.Models.AllData;
import com.example.bookshop.Utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<AllData> call = apiInterface.getBookItems();

        call.enqueue(new Callback<AllData>() {
            @Override
            public void onResponse(Call<AllData> call, Response<AllData> response) {
                Log.i("items", response.body().getTotalItems()+"");
                Log.i("Reponse", response.body().getAllItems().get(0).getVolumeInfoData().getDescription()+"");
            }

            @Override
            public void onFailure(Call<AllData> call, Throwable t) {

            }
        });
    }
}