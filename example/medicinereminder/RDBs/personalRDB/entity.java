package com.example.medicinereminder.RDBs.personalRDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class entity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "reminderdetails")
    public String reminderdetails;

    @ColumnInfo(name = "alarmid")
    public long aid;

    @ColumnInfo(name="alarmtime")
    public long alaramtime;

    public entity(String reminderdetails, long aid, long alaramtime) {
        this.reminderdetails = reminderdetails;
        this.aid = aid;
        this.alaramtime = alaramtime;
    }

    public String getReminderdetails() {
        return reminderdetails;
    }

    public void setReminderdetails(String reminderdetails) {
        this.reminderdetails = reminderdetails;
    }

    public long getAid() {
        return aid;
    }

    public void setAid(long aid) {
        this.aid = aid;
    }

    public long getAlaramtime() {
        return alaramtime;
    }

    public void setAlaramtime(long alaramtime) {
        this.alaramtime = alaramtime;
    }
}
