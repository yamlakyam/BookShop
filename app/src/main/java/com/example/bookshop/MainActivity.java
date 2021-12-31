package com.example.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookshop.Adapters.PaginationListAdapter;
import com.example.bookshop.Api.ApiInterface;
import com.example.bookshop.Models.AllData;
import com.example.bookshop.Models.BookItem;
import com.example.bookshop.Models.ItemData;
import com.example.bookshop.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

    ApiInterface apiInterface = retrofit.create(ApiInterface.class);

    private PaginationListAdapter adapter;
    private List<BookItem> values = new ArrayList<>();

    int initialStartingIndex = 0;
    int initialEndingIndex = 10;

    int totalBookItems = 0;

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button3);

        ListView listView = (ListView) findViewById(R.id.pagination_listview);
//        TableLayout listView = findViewById(R.id.pagination_tableLayout);
        adapter = new PaginationListAdapter(MainActivity.this, values);
        listView.setAdapter(adapter);

        Call<AllData> call = apiInterface.getInitialBookItems();
        call.enqueue(new Callback<AllData>() {
            @Override
            public void onResponse(Call<AllData> call, Response<AllData> response) {

                totalBookItems = response.body().getTotalItems();

                addBookItems(response);
                adapter.notifyDataSetChanged();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fetchNextPage();
                    }
                });

            }

            @Override
            public void onFailure(Call<AllData> call, Throwable t) {

            }
        });
    }


    private void fetchNextPage() {
        if (initialStartingIndex + 10 < totalBookItems) {
            initialStartingIndex += 10;
        } else {
            initialStartingIndex = totalBookItems - 1;
        }


        Call<AllData> call = apiInterface.getNextBookItems(initialStartingIndex);
        call.enqueue(new Callback<AllData>() {
            @Override
            public void onResponse(Call<AllData> call, Response<AllData> response) {

                if (response.body() != null) {
                    totalBookItems = response.body().getTotalItems();

                    addBookItems(response);
                    adapter.notifyDataSetChanged();
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (initialStartingIndex < totalBookItems) {
                                fetchNextPage();
                            }
                        }
                    });

                }

            }

            @Override
            public void onFailure(Call<AllData> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Check your Network Connection!",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addBookItems(Response<AllData> response){
        for (ItemData repo : response.body().getAllItems()) {
            String imageUrl;
            if (repo.getVolumeInfoData().getImageLinksData() != null) {
                imageUrl = repo.getVolumeInfoData().getImageLinksData().getThumbnail();
            } else {
                imageUrl = "https://cdn5.vectorstock.com/i/1000x1000/59/94/blank-book-cover-perspective-orange-vector-2105994.jpg";
            }

            values.add(new BookItem(
                    repo.getVolumeInfoData().getTitle(),
                    repo.getVolumeInfoData().getDescription(), imageUrl));

        }
    }
}