package com.example.medicinereminder.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.medicinereminder.R;
import com.example.medicinereminder.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    //Declaring All buttons as variable from activity_main.xml
    Button gotomedicine,gotopersonal,gotoreport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //maping variable with activity_main.xml
        gotomedicine=findViewById(R.id.medicine_reminder);
        gotopersonal=findViewById(R.id.personal_reminder);
        gotoreport=findViewById(R.id.Report_reminder);

        //openning other activity by cllick on button
        gotomedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, MedicineActivity.class);
                startActivity(intent);

            }
        });
        gotopersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, PersonalActivity.class);
                startActivity(intent);

            }
        });
        gotoreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ReposrtActivity.class);
                startActivity(intent);

            }
        });



    }
}