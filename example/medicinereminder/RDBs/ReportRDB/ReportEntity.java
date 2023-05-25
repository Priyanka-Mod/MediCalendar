package com.example.medicinereminder.RDBs.ReportRDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ReportEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "Reportname")
    public String reportname;

    @ColumnInfo(name = "reportid")
    public long reportid;

    @ColumnInfo(name="reportnotifytime")
    public long reportnotifytime;

    public ReportEntity(String reportname, long reportid, long reportnotifytime) {
        this.reportname = reportname;
        this.reportid = reportid;
        this.reportnotifytime = reportnotifytime;
    }

    public String getReportname() {
        return reportname;
    }

    public void setReportname(String reportname) {
        this.reportname = reportname;
    }

    public long getReportid() {
        return reportid;
    }

    public void setReportid(long reportid) {
        this.reportid = reportid;
    }

    public long getReportnotifytime() {
        return reportnotifytime;
    }

    public void setReportnotifytime(long reportnotifytime) {
        this.reportnotifytime = reportnotifytime;
    }
}
