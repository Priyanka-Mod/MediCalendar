package com.example.medicinereminder.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.medicinereminder.Adapter.PillAdapeter;
import com.example.medicinereminder.AddActivities.addpillreminder;
import com.example.medicinereminder.R;
import com.example.medicinereminder.RDBs.medicineRDB.MedicineDAO;
import com.example.medicinereminder.RDBs.medicineRDB.MedicineDB;
import com.example.medicinereminder.RDBs.medicineRDB.MedicineEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MedicineActivity extends AppCompatActivity {
    FloatingActionButton addMedicineReminder;
    RecyclerView MedicineReminderList;
    PillAdapeter adapter;
    List<MedicineEntity> data =new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {

        super.onRestart();
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        addMedicineReminder=findViewById(R.id.addmedicine);
        MedicineDB rdb= Room.databaseBuilder(getApplicationContext(), MedicineDB.class,"pillreminder").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        MedicineDAO dao=rdb.medicineDAO();
        if(rdb != null) {
            data = dao.getalldata();
        }
        MedicineReminderList=findViewById(R.id.med_rv);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        MedicineReminderList.setLayoutManager(layoutManager);
        adapter=new PillAdapeter(data);
        MedicineReminderList.setAdapter(adapter);
        addMedicineReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MedicineActivity.this, addpillreminder.class);
                startActivity(intent);
                finish();
            }
        });
        adapter.setOnitemClick(new PillAdapeter.pillOnitemClicklistner() {
            @Override
            public void onItemclick(int position) {
                //for deleting we need to  write and implement here
                MedicineEntity model= data.get(position);
                dao.deleterow(model.getAidforpill());
                data.remove(position);
                adapter.notifyDataSetChanged();



            }
        });

    }
}