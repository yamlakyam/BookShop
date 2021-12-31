package com.example.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TableLayout;

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
    Call<AllData> call = apiInterface.getBookItems();

    private PaginationListAdapter adapter;
    private List<BookItem> values = new ArrayList<>();

    int initialStartingIndex = 0;
    int initialEndingIndex = 10;

    int totalBookItems = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.pagination_listview);
//        TableLayout listView = findViewById(R.id.pagination_tableLayout);
        adapter = new PaginationListAdapter(MainActivity.this, values);
        listView.setAdapter(adapter);


        call.enqueue(new Callback<AllData>() {
            @Override
            public void onResponse(Call<AllData> call, Response<AllData> response) {
                Log.i("items", response.body().getTotalItems() + "");
                Log.i("Reponse", response.body().getAllItems().get(0).getVolumeInfoData().getDescription() + "");

                totalBookItems = response.body().getTotalItems();

                for (ItemData repo : response.body().getAllItems()) {
                    values.add(new BookItem(repo.getVolumeInfoData().getTitle(),
                            repo.getVolumeInfoData().getDescription(),
                            repo.getVolumeInfoData().getImageLinksData().getThumbnail()));
                }

                adapter.notifyDataSetChanged();
                fetchNextPage();

            }

            @Override
            public void onFailure(Call<AllData> call, Throwable t) {

            }
        });
    }


    private void fetchNextPage() {
//        GitHubPagelinksUtils pagelinksUtils =
//                new GitHubPagelinksUtils(response.headers());
//        String next = pagelinksUtils.getNext();
//
//        Log.d("Header", response.headers().get("Link"));
//
//        if (TextUtils.isEmpty(next)) {
//            return; // nothing to do
//        }
//
//        Call<List<GitHubRepo>> call = service.reposForUserPaginate(next);
//        call.enqueue(callback);

//        if (initialEndingIndex + 10 < totalBookItems) {
//            initialEndingIndex += 10;
//        } else {
//            initialEndingIndex = totalBookItems - 1;
//        }
        if (initialStartingIndex + 10 < totalBookItems) {
            initialStartingIndex += 10;
        } else {
            initialStartingIndex = totalBookItems-1;
        }


        Call<AllData> call = apiInterface.getBooks(initialStartingIndex);

        Log.i("initialStartingIndex", initialStartingIndex + "");
        Log.i("initialEndingIndex", initialEndingIndex + "");
        call.enqueue(new Callback<AllData>() {
            @Override
            public void onResponse(Call<AllData> call, Response<AllData> response) {

                if (response.body() != null) {
                    totalBookItems = response.body().getTotalItems();
                    for (ItemData repo : response.body().getAllItems()) {
                        Log.i("ID", repo.getId());
                        Log.i("initialStartingIndex", initialStartingIndex + "");

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

                    Log.i("theItems", values + "");

                    adapter.notifyDataSetChanged();

                    Log.i("NOTIFIED", "?: ");
                    if (initialStartingIndex < totalBookItems) {
                        fetchNextPage();
                    }
                }

            }

            @Override
            public void onFailure(Call<AllData> call, Throwable t) {

            }
        });
    }
}