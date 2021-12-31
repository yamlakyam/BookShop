package com.example.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class BookDetailActivity extends AppCompatActivity {
    TextView bookTitle, bookDesc;
    String title, desc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        bookTitle = findViewById(R.id.bookTitle);
        bookDesc = findViewById(R.id.bookDesc);

        if (getIntent().getStringExtra("TITLE") != null) {
            title = getIntent().getStringExtra("TITLE");
        }
        if (getIntent().getStringExtra("DESC") != null) {
            desc = getIntent().getStringExtra("DESC");
        }

        bookTitle.setText(title);
        bookDesc.setText(desc);
    }
}