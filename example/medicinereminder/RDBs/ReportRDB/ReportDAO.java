package com.example.medicinereminder.RDBs.ReportRDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.medicinereminder.RDBs.medicineRDB.MedicineEntity;

import java.util.List;

@Dao
public interface ReportDAO {
    @Insert
    void insertall(ReportEntity reportEntity);


    @Query("SELECT * FROM  ReportEntity")
    List<ReportEntity> getalldata();

    @Query("delete from ReportEntity where Reportname=:details")
    void deletereminderrow(String details);

    @Query("SELECT Reportname FROM ReportEntity")
    String []remindername();

    @Query("select reportid from ReportEntity where Reportname =:name")
    long getid(String name);

    @Query("delete from ReportEntity where reportid=:id")
    void deleterow(long id);
}
