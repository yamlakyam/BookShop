package com.example.bookshop.Models;

public class VolumeInfoData {
    private String title;
    private String description;
    private VolumeInfoData volumeInfoData;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public VolumeInfoData getVolumeInfoData() {
        return volumeInfoData;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVolumeInfoData(VolumeInfoData volumeInfoData) {
        this.volumeInfoData = volumeInfoData;
    }
}

