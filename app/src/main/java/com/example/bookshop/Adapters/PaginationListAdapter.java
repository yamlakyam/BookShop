package com.example.bookshop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bookshop.Models.BookItem;
import com.example.bookshop.R;

import java.util.List;

public class PaginationListAdapter extends ArrayAdapter<BookItem> {

    private Context context;
    private List<BookItem> values;

    public PaginationListAdapter(Context context, List<BookItem> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.book_tem_pagination, parent, false);
        }

        TextView textView = (TextView) row.findViewById(R.id.book_item_title);

        BookItem item = values.get(position);
        String message = item.getTitle();
        textView.setText(message);

        return row;
    }
}
