package com.example.softwareengineeringcode;

import android.content.Context;

import java.util.List;

public class AccessDB {
    private Database db;

    public AccessDB(Context con) {
        db = new Database(con);//The Class That Represents the .db File
    }

    public Report getNewestReport() {
        return db.getNewestReport();
    }

    public void sendReport(Report r) {
        db.submitRecord(r);
    }

    public List<Report> getAllReports() {
        return db.getReports();
    }
}
