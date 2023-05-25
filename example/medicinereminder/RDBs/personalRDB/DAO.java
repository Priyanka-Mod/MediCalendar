package com.example.medicinereminder.RDBs.personalRDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAO {
    @Insert
    void insertall(entity entity);

    @Query("SELECT * FROM entity")
    List<entity> getalldata();

    @Query("delete from entity where reminderdetails=:details")
    void deletereminderrow(String details);

    @Query("SELECT reminderdetails FROM entity")
    String []remindername();

    @Query("select alarmid from entity where reminderdetails=:name")
    long getid(String name);

    @Query("delete from entity where alarmid=:id")
    void deleterow(long id);



}
