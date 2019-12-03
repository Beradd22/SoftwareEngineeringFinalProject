package com.example.softwareengineeringcode;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

class Database {
    private SQLiteDatabase db;
    private Context context;

    public Database(Context con) {
        this.context = con;
    }

    public List<Report> getReports() {
        Report report = new Report();
        String DBFileName = "IncidentReportApp.db";
        List<Report> reportList = new ArrayList<>();

        //If DB Does Not Exist Create It
        if (!(context.getDatabasePath(DBFileName).exists()) || context.getDatabasePath(DBFileName) == null) {
            try {
                //Creates DB
                String SQL = "CREATE TABLE \"Reports\" ( \"ReportID\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, \"Title\" TEXT NOT NULL, \"WeatherType\" TEXT NOT NULL, \"Location\" TEXT NOT NULL, \"DateTime\" TEXT NOT NULL, \"Details\" TEXT NOT NULL )";
                String SQL2 = "CREATE TABLE \"Pictures\" ( \"PictureID\" INTEGER NOT NULL DEFAULT 1 PRIMARY KEY AUTOINCREMENT UNIQUE, \"ReportID\" INTEGER NOT NULL, \"Binary\" BLOB NOT NULL )";
                db = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath(DBFileName), null);
                db.execSQL(SQL);
                db.execSQL(SQL2);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {//If DB Already Exist
            try {
                //Open Database
                db = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath(DBFileName), null);

                //Query For Reports
                String query = "SELECT * From Reports";
                Cursor cursor = db.rawQuery(query, null);

                //Manipulating Cursor Data
                if (cursor != null) {
                    int count = 0;
                    String row = "";
                    if (cursor.moveToFirst()) {
                        while (!cursor.isAfterLast()) {
                            //Fill Report
                            report.title = cursor.getString(1);
                            report.wType = cursor.getString(2);
                            report.location = cursor.getString(3);
                            report.dateTime = cursor.getString(4);
                            report.details = cursor.getString(5);
                            //Add To List Of Reports
                            reportList.add(report);
                            //Move to Next Report
                            cursor.moveToNext();
                        }
                    }
                    cursor.close(); //important
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        db.close(); //Close Database Connection
        return reportList; //assuming database is already created will be populated
    }


    public Report getNewestReport() {
        Report report = new Report();
        String DBFileName = "IncidentReportApp.db";

        //If DB Does Not Exist Create It
        if (!(context.getDatabasePath(DBFileName).exists()) || context.getDatabasePath(DBFileName) == null) {
            try {
                //Creates DB
                String SQL = "CREATE TABLE \"Reports\" ( \"ReportID\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, \"Title\" TEXT NOT NULL, \"WeatherType\" TEXT NOT NULL, \"Location\" TEXT NOT NULL, \"DateTime\" TEXT NOT NULL, \"Details\" TEXT NOT NULL )";
                String SQL2 = "CREATE TABLE \"Pictures\" ( \"PictureID\" INTEGER NOT NULL DEFAULT 1 PRIMARY KEY AUTOINCREMENT UNIQUE, \"ReportID\" INTEGER NOT NULL, \"Binary\" BLOB NOT NULL )";
                db = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath(DBFileName), null);
                db.execSQL(SQL);
                db.execSQL(SQL2);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {//If DB Does Exist
            try {
                //Open Database
                db = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath(DBFileName), null);

                //Query For Reports
                String query = "SELECT * FROM Reports ORDER BY ReportID DESC LIMIT 1";
                Cursor cursor = db.rawQuery(query, null);
                String reportID = "";

                //Manipulating Cursor Data
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        //Fill Report
                        reportID = cursor.getString(0);
                        report.title = cursor.getString(1);
                        report.wType = cursor.getString(2);
                        report.location = cursor.getString(3);
                        report.dateTime = cursor.getString(4);
                        report.details = cursor.getString(5);
                    }
                    cursor.close();
                }

                //Get Pictures Relating to Report
                query = "SELECT * FROM Pictures WHERE ReportID = " + "reportID";
                cursor = db.rawQuery(query, null);

                //Add All Pictures From Database into Pictures List -A picture is a byte[]
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        //first picture
                        report.pictures.add(cursor.getBlob(0));
                        //all other pictures
                        while (cursor.moveToNext()) {
                            report.pictures.add(cursor.getBlob(0));
                        }
                    }
                }
                cursor.close();

                return report;

            } catch (
                    Exception e) {
                e.printStackTrace();
            }
        }

        db.close(); //Close Database Connection
        return report; //will be NULL
    }

    public void submitRecord(Report r) {
        String DBFileName = "IncidentReportApp.db";
        Boolean passed = false;

        //If DB Does Not Exist Create It
        if (!(context.getDatabasePath(DBFileName).exists()) || context.getDatabasePath(DBFileName) == null)
        {
            try {
                //Creates DB
                String SQL = "CREATE TABLE \"Reports\" ( \"ReportID\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, \"Title\" TEXT NOT NULL, \"WeatherType\" TEXT NOT NULL, \"Location\" TEXT NOT NULL, \"DateTime\" TEXT NOT NULL, \"Details\" TEXT NOT NULL )";
                String SQL2 = "CREATE TABLE \"Pictures\" ( \"PictureID\" INTEGER NOT NULL DEFAULT 1 PRIMARY KEY AUTOINCREMENT UNIQUE, \"ReportID\" INTEGER NOT NULL, \"Binary\" BLOB NOT NULL )";
                db = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath(DBFileName), null);
                db.execSQL(SQL);
                db.execSQL(SQL2);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        //Add to Reports table in DB
        String SQL = "INSERT INTO Reports(Title,WeatherType,Location,DateTime,Details) VALUES ('" + r.title + "','" + r.wType + "','" + r.location + "','" + r.dateTime + "','" + r.details + "')'";

        try {
            //Puts Data into Database
            db = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath(DBFileName), null);
            db.execSQL(SQL);
            passed = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (passed) {//If you fail to Insert Data into Reports you Should NOT Insert Data Into Pictures
            try {
                //Get the Highest ReportID
                String query = "SELECT MAX(ReportID) From Reports";
                Cursor cursor = db.rawQuery(query, null);

                if (cursor.moveToFirst()) {
                    for (int i = 0; i < r.pictures.size() - 1; i++) {
                        //Add to Pictures table in DB
                        SQL = "INSERT INTO Pictures(ReportID, Binary) VALUES (" + cursor.getString(0) + " , " + r.pictures.get(i).toString() + ")"; //column 0 of cursor is Highest ReportID; These are both non-strings
                        db.execSQL(SQL);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();

            }
            db.close(); //Close Database Connection
        }
    }


}
