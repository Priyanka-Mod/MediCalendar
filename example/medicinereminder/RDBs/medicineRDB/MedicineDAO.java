package com.example.medicinereminder.RDBs.medicineRDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface MedicineDAO {

    @Insert
    void insertall(MedicineEntity entity);


    @Query("SELECT * FROM  MedicineEntity")
    List<MedicineEntity> getalldata();

    @Query("delete from MedicineEntity where pillname=:details")
    void deletereminderrow(String details);

    @Query("SELECT pillname FROM MedicineEntity")
    String []remindername();

    @Query("select alarmidforpill from MedicineEntity where pillname =:name")
    long getid(String name);

    @Query("delete from MedicineEntity where alarmidforpill=:id")
    void deleterow(long id);
}
