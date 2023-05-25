package com.example.medicinereminder.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.medicinereminder.Adapter.reportAdapter;
import com.example.medicinereminder.AddActivities.AddReportReminder;

import com.example.medicinereminder.R;
import com.example.medicinereminder.RDBs.ReportRDB.ReportDAO;
import com.example.medicinereminder.RDBs.ReportRDB.ReportDB;
import com.example.medicinereminder.RDBs.ReportRDB.ReportEntity;
import com.example.medicinereminder.RDBs.personalRDB.entity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ReposrtActivity extends AppCompatActivity {
    FloatingActionButton addreport;
    RecyclerView reportreminderList;
    reportAdapter adapter;
    List<ReportEntity> data=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reposrt);
        ReportDB rdb = Room.databaseBuilder(getApplicationContext(), ReportDB.class, "personalreminder").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        ReportDAO dao = rdb.reportDAO();
        data=dao.getalldata();
        addreport=findViewById(R.id.addreport);
        reportreminderList=findViewById(R.id.report_rv);


        addreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ReposrtActivity.this, AddReportReminder.class);
                startActivity(intent);
                finish();
            }
        });
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        reportreminderList.setLayoutManager(layoutManager);
        adapter=new reportAdapter(data);
        reportreminderList.setAdapter(adapter);
        adapter.setOnitemClick(new reportAdapter.reportOnitemClicklistner() {
            @Override
            public void onItemclick(int position) {
                ReportEntity model=data.get(position);
                dao.deleterow(model.getReportid());
                data.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
}