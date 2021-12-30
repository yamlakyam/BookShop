package com.example.bookshop.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllData {

    @SerializedName("totalItems")
    private int totalItems;

    @SerializedName("items")
    private ArrayList<ItemData> allItems;

    public int getTotalItems() {
        return totalItems;
    }


    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public ArrayList<ItemData> getAllItems() {
        return allItems;
    }

    public void setAllItems(ArrayList<ItemData> allItems) {
        this.allItems = allItems;
    }
}
