package com.example.softwareengineeringcode;

import java.util.List;

public class AccessDB {
    private Database db = new Database();//The Class That Represents the .db File

    public void sendReport(Report r) {
        db.submitRecord(r);
    }

    public List<Report> getAllReports(){
        return db.getReports();
    }
}
