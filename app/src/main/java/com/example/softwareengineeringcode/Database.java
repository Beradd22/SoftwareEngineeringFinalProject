package com.example.softwareengineeringcode;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

class Database {
    SQLiteDatabase db;


    public List<Report> getReports() {
        Report report = new Report();
        String DBFileLocation = "SoftwareEngineeringFinalProject\\app\\src\\main\\assets\\databases\\";
        String DBFileName = "IncidentReportApp.db";
        String query = "SELECT * From Reports";
        List<Report> reportList = new ArrayList<>();

        try {
            //Open Database
            db = SQLiteDatabase.openOrCreateDatabase(DBFileLocation + DBFileName, null);
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                while (cursor.moveToNext()) {
                    for (int i = 0; i < cursor.getCount(); i++) {
                        String row = cursor.getString(i);
                        //PUT DATA INTO report OBJ then add OBJ to reportList

                        reportList.add(report);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return reportList;
        return reportList;
    }


    public Report getReport(Report r) {
        Report report = new Report();
        String DBFileLocation = "SoftwareEngineeringFinalProject\\app\\src\\main\\assets\\databases\\";
        String DBFileName = "IncidentReportApp.db";
        String query = "SELECT 1 From Reports where Reports.Title=" + r.title + " AND Reports.DateTime=" + r.dateTime + ")";

        try {
            //Open Database
            db = SQLiteDatabase.openOrCreateDatabase(DBFileLocation + DBFileName, null);
            Cursor cursor = db.rawQuery(query, null);

            String row = cursor.getString(0);
            //PUT DATA INTO report OBJ then return the OBJ

            return report;

        } catch (Exception e) {
            e.printStackTrace();
        }
        //return reportList;
        return report; //will be NULL
    }


    private void submitRecord(Report r, Blob b) {
        String DBFileLocation = "SoftwareEngineeringFinalProject\\app\\src\\main\\assets\\databases\\";
        String DBFileName = "IncidentReportApp.db";

        //Add to Reports table in DB
        String SQL = "INSERT INTO Reports(Title,WeatherType,Location,DateTime,Details) VALUES (" + r.title + "," + r.wType + "," + r.location + "," + r.dateTime + r.details + ")";

        //Open Database
        try {
            db = SQLiteDatabase.openOrCreateDatabase(DBFileLocation + DBFileName, null);
            db.execSQL(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String query = "SELECT MAX(ReportID) From Reports";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            for (int i = 0; i < r.pictures.size() - 1; i++) {
                //Add to Pictures table in DB
                SQL = "INSERT INTO Pictures(ReportID, Binary) VALUES (" + cursor.toString() + b + ")";
                db.execSQL(SQL);
            }
        }
    }

}
