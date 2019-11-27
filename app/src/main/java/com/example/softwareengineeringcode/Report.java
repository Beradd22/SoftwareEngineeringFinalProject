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
    List<Blob> pictures;
    String details;
    String wType;
    String location;
    String dateTime;

    public Report() {
        this.title = "";
        this.pictures = new ArrayList<>();
        this.details = "";
        this.wType = "";
        this.location = "";
        this.dateTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }

}
