package com.example.bookshop.Models;

import com.google.gson.annotations.SerializedName;

public class ItemData {

    @SerializedName("id")
    private String id;

    @SerializedName("volumeInfo")
    private VolumeInfoData volumeInfoData;

    public String getId() {
        return id;
    }

    public VolumeInfoData getVolumeInfoData() {
        return volumeInfoData;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVolumeInfoData(VolumeInfoData volumeInfoData) {
        this.volumeInfoData = volumeInfoData;
    }
}
