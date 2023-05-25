package com.example.medicinereminder.RDBs.medicineRDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MedicineEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "pillname")
    public String pillname;

    @ColumnInfo(name = "alarmidforpill")
    public long aidforpill;

    @ColumnInfo(name="alarmtimeforpill")
    public long alaramtimeforpill;

    public MedicineEntity(String pillname, long aidforpill, long alaramtimeforpill) {
        this.pillname = pillname;
        this.aidforpill = aidforpill;
        this.alaramtimeforpill = alaramtimeforpill;
    }

    public String getPillname() {
        return pillname;
    }

    public void setPillname(String pillname) {
        this.pillname = pillname;
    }

    public long getAidforpill() {
        return aidforpill;
    }

    public void setAidforpill(long aidforpill) {
        this.aidforpill = aidforpill;
    }

    public long getAlaramtimeforpill() {
        return alaramtimeforpill;
    }

    public void setAlaramtimeforpill(long alaramtimeforpill) {
        this.alaramtimeforpill = alaramtimeforpill;
    }
}
