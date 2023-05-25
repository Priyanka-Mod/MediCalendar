package com.example.medicinereminder.RDBs.personalRDB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {entity.class},version = 2)
public  abstract  class AppDB extends RoomDatabase {
    public  abstract DAO dao();
}
