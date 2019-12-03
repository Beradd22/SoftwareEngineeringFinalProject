package com.example.softwareengineeringcode;

import android.graphics.Bitmap;
import android.graphics.Picture;

import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Report {

    String title;
    List<byte[]> pictures;
    String details;
    String wType;
    String location;
    String dateTime;

    public Report() {
        this.title = "Empty Report";
        this.pictures = new ArrayList<>();
        this.details = "";
        this.wType = "";
        this.location = "";
        this.dateTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() { return title; }

    public void setPictures(List<byte[]> pictures) {
        this.pictures = pictures;
    }

    public List<byte[]> getPictures() { return pictures; }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails() { return details; }

    public void setwType(String wType) {
        this.wType = wType;
    }

    public String getwType() { return wType; }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDateTime() { return dateTime; }
}
