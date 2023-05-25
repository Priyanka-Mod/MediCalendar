package com.example.medicinereminder.Activities;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.medicinereminder.R;
import com.example.medicinereminder.RDBs.personalRDB.AppDB;
import com.example.medicinereminder.RDBs.personalRDB.DAO;
import com.example.medicinereminder.RDBs.personalRDB.entity;
import com.example.medicinereminder.Adapter.adapter_rv;
import com.example.medicinereminder.AddActivities.addpersonalreminder;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PersonalActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addpersonal;
    adapter_rv adapter;
    List<entity> list;

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        list=new ArrayList<>();
        addpersonal=findViewById(R.id.addpersonal);
        recyclerView=findViewById(R.id.personal_rv);

        AppDB rdb= Room.databaseBuilder(getApplicationContext(),AppDB.class,"personalreminder").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        DAO dao=rdb.dao();
        if(rdb != null){
            list=dao.getalldata();
        }
        addpersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PersonalActivity.this, addpersonalreminder.class);
                startActivity(intent);
                finish();


            }
        });

        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new adapter_rv(list,getApplicationContext());
        recyclerView.setAdapter(adapter);
        adapter.setOnitemClick(new adapter_rv.OnitemClicklistner() {
            @Override
            public void onItemclick(int position) {
                //for deleting we need to  write and implement here
                entity model=list.get(position);
                dao.deleterow(model.getAid());
                list.remove(position);
                adapter.notifyDataSetChanged();



            }
        });
    }

}