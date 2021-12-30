package com.example.bookshop.Models;

import com.google.gson.annotations.SerializedName;

public class VolumeInfoData {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("imageLinks")
    private ImageLinksData imageLinksData;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageLinksData getImageLinksData() {
        return imageLinksData;
    }

    public void setImageLinksData(ImageLinksData imageLinksData) {
        this.imageLinksData = imageLinksData;
    }
}

