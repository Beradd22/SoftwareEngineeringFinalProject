package com.example.softwareengineeringcode;

import android.graphics.Bitmap;
import android.graphics.Picture;

import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Report {

    public Report() {
        String title = "";
        List<Blob> pictures = new ArrayList<>();
        String details = "";
        String wType = "";
        String location = "";
        String dateTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }

}
