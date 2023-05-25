package com.example.medicinereminder.RDBs.ReportRDB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.medicinereminder.RDBs.medicineRDB.MedicineEntity;

@Database(entities = {ReportEntity.class},version = 1)
public abstract class ReportDB extends RoomDatabase {
    public abstract ReportDAO reportDAO();

}
