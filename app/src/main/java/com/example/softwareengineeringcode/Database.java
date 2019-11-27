package com.example.softwareengineeringcode;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

    class Database {
        SQLiteDatabase db;


        public String getReports() {
            Report report = new Report();
            String DBFileLocation = "C:\\Users\\khari\\AndroidStudioProjects\\SQLiteDBPractice\\Assets";
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
                            return row;
                            //PUT DATA INTO report OBJ then add OBJ to reportList

                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //return reportList;
            return "yo";
        }


        public String getReport(Report r) {
            Report report = new Report();
            String DBFileLocation = "C:\\Users\\khari\\AndroidStudioProjects\\SQLiteDBPractice\\Assets";
            String DBFileName = "IncidentReportApp.db";
            String query = "SELECT 1 From Reports where Reports.Title=" + r.title + " AND Reports.DateTime=" + r.dateTime + ")";

            try {
                //Open Database
                db = SQLiteDatabase.openOrCreateDatabase(DBFileLocation + DBFileName, null);
                Cursor cursor = db.rawQuery(query, null);

                String row = cursor.getString(0);
                return row;
                //PUT DATA INTO report OBJ then return the OBJ


            } catch (Exception e) {
                e.printStackTrace();
            }
            //return reportList;
            return "yo";
        }


        private String submitRecord(Report r, Blob b) {
            String DBFileLocation = "C:\\Users\\khari\\AndroidStudioProjects\\SQLiteDBPractice\\Assets";
            String DBFileName = "IncidentReportApp.db";

            //Add to Reports table in DB
            String SQL = "INSERT INTO Reports(Title,WeatherType,Location,DateTime, Details) VALUES (" + r.title + "," + r.wType + "," + r.location + "," + r.dateTime + r.details + ")";

            //Open Database
            try {
                db = SQLiteDatabase.openOrCreateDatabase(DBFileLocation + DBFileName, null);
                db.execSQL(SQL);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String query = "SELECT 1 From Reports ORDER BY ReportID DESC";
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                //Grab NEWEST Report ID and store it
                String row = cursor.getString(0);
                //return row;
            }


            for (int i = 0; i < r.pictures.size() - 1; i++) {
                //Add to Pictures table in DB
                SQL = "INSERT INTO Pictures(ReportID, Binary) VALUES (" + cursor.getString(0) + b.toString() + ")";
            }

            return "bad";
        }

    }
