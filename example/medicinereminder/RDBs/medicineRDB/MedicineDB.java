package com.example.medicinereminder.RDBs.medicineRDB;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.medicinereminder.RDBs.personalRDB.entity;

@Database(entities = {MedicineEntity.class},version = 1)
public abstract class MedicineDB extends RoomDatabase {
    public abstract MedicineDAO medicineDAO();


}
