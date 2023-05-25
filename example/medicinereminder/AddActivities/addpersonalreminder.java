package com.example.medicinereminder.AddActivities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.room.Room;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.medicinereminder.Activities.PersonalActivity;
import com.example.medicinereminder.RDBs.personalRDB.AppDB;
import com.example.medicinereminder.RDBs.personalRDB.DAO;
import com.example.medicinereminder.RDBs.personalRDB.entity;
import com.example.medicinereminder.databinding.ActivityAddpersonalreminderBinding;
import com.example.medicinereminder.recivers.medicinereciver;
import com.example.medicinereminder.recivers.personalreciver;
import com.example.medicinereminder.recivers.reportreiciver;

import java.util.Calendar;
import java.util.Date;

public class addpersonalreminder extends AppCompatActivity {


    ActivityAddpersonalreminderBinding binding;
    Calendar calendar,cal;
    AlarmManager manager;
    Date date;
    PendingIntent pendingIntent;
    EditText getreminderDetails;
    TextView showtd;
    Button save;
    Button settime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddpersonalreminderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createnotificationchannel();

        getreminderDetails=binding.enterPr;
        showtd=binding.selectedtime;
        save=binding.button;
        settime=binding.selecttime;


        calendar=Calendar.getInstance();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getreminderDetails != null) {
                    long id = System.currentTimeMillis();
                    setalarm();
                    AppDB rdb = Room.databaseBuilder(getApplicationContext(), AppDB.class, "personalreminder").allowMainThreadQueries().build();
                    DAO dao = rdb.dao();
                    dao.insertall(new entity(getreminderDetails.getText().toString(), id, calendar.getTimeInMillis()));
                    Intent i = new Intent(addpersonalreminder.this, PersonalActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        });
        settime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showtimepicker();

            }
        });


    }
    private void showtimepicker() {
        cal=Calendar.getInstance();
        int hours=cal.get(Calendar.HOUR_OF_DAY);
        int min=cal.get(Calendar.MINUTE);
        TimePickerDialog dialog=new TimePickerDialog(addpersonalreminder.this, androidx.appcompat.R.style.Theme_AppCompat_DayNight_Dialog, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                calendar.set(Calendar.HOUR_OF_DAY,i);
                calendar.set(Calendar.MINUTE,i1);
                date=new Date(calendar.getTimeInMillis());
                showtd.setText(date.toString());

            }
        },hours,min,false);

        dialog.show();

    }

    private void createnotificationchannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name="channelname";
            String description="channle for alarm manager";
            int importance= NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel=new NotificationChannel("testing",name,importance);
            channel.setDescription(description);

            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
    private void setalarm() {
        final int aid= (int) System.currentTimeMillis();
        manager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent=new Intent(addpersonalreminder.this, reportreiciver.class);
        intent.putExtra("notificationname",getreminderDetails.getText().toString());
        intent.putExtra("id",calendar.getTimeInMillis());
        pendingIntent=PendingIntent.getBroadcast(this,aid,intent,PendingIntent.FLAG_MUTABLE);
        date=new Date(calendar.getTimeInMillis());
        Toast.makeText(this, date.toString(), Toast.LENGTH_SHORT).show();
        manager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

    }
}