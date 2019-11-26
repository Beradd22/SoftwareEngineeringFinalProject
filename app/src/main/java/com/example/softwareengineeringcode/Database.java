package com.example.softwareengineeringcode;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Database {
    SQLiteDatabase db;



    public String getReports() {
        String DBFileLocation = "C:\\Users\\khari\\AndroidStudioProjects\\SQLiteDBPractice\\Assets";
        String DBFileName = "IncidentReportApp.db";
        String query = "SELECT * From Reports";
        List<Report> reportList = new ArrayList<>();

        try {
            //Open Database
            db = SQLiteDatabase.openOrCreateDatabase(DBFileLocation + DBFileName, null);
            Cursor cursor = db.rawQuery(query, null);
            if(cursor.moveToFirst()) {
                while (cursor.moveToNext()) {
                    for (int i = 0; i < cursor.getCount(); i++) {
                        String row = cursor.getString(i);
                        return row;
                        // Report report = new Report();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return reportList;
        return "yo";
    }


    public String getReport() {
        String DBFileLocation = "C:\\Users\\khari\\AndroidStudioProjects\\SQLiteDBPractice\\Assets";
        String DBFileName = "IncidentReportApp.db";
        String query = "SELECT * From Reports";
        List<Report> reportList = new ArrayList<>();

        try {
            //Open Database
            db = SQLiteDatabase.openOrCreateDatabase(DBFileLocation + DBFileName, null);
            Cursor cursor = db.rawQuery(query, null);
            if(cursor.moveToFirst()) {
                while (cursor.moveToNext()) {
                    for (int i = 0; i < cursor.getCount(); i++) {
                        String row = cursor.getString(i);
                        return row;
                        // Report report = new Report();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return reportList;
        return "yo";
    }


    private String submitRecord(Report r){
        String DBFileLocation = "C:\\Users\\khari\\AndroidStudioProjects\\SQLiteDBPractice\\Assets";
        String DBFileName = "IncidentReportApp.db";

        //Add to Reports table in DB
        String SQL = "INSERT INTO Reports(Title,WeatherType,Location,DateTime) VALUES (" + r.title + "," + r.wType + "," + r.location + "," + r.dateTime + ")";

        //Open Database
        try {
            db = SQLiteDatabase.openOrCreateDatabase(DBFileLocation + DBFileName, null);
            db.execSQL(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String query = "SELECT 1 From Reports ORDER BY ReportID DESC";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            //Grab NEWEST Report ID and store it
            String row = cursor.getString(0);
            //return row;
        }

        /*
        for(int i = 0; i<r.picture.size()-1; i++) {
            //Add to Pictures table in DB
            SQL = "INSERT INTO Pictures(ReportID, Binary, DateTime) VALUES (" +  + r.picture.get(i).binary + r.picture.get(i).dateTime + ")";
        }
        */

        return "bad";
    }


}
