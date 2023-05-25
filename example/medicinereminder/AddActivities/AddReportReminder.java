package com.example.medicinereminder.AddActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
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
import com.example.medicinereminder.Activities.ReposrtActivity;
import com.example.medicinereminder.R;
import com.example.medicinereminder.RDBs.ReportRDB.ReportDAO;
import com.example.medicinereminder.RDBs.ReportRDB.ReportDB;
import com.example.medicinereminder.RDBs.ReportRDB.ReportEntity;
import com.example.medicinereminder.RDBs.personalRDB.AppDB;
import com.example.medicinereminder.RDBs.personalRDB.DAO;
import com.example.medicinereminder.RDBs.personalRDB.entity;
import com.example.medicinereminder.recivers.medicinereciver;
import com.example.medicinereminder.recivers.reportreiciver;

import java.util.Calendar;
import java.util.Date;

public class AddReportReminder extends AppCompatActivity {
    EditText reportname;
    AlarmManager manager;
    PendingIntent pendingIntent;
    Button setTimeforrp;
    Button selectdate;
    Button addReportReminder;
    Calendar cal,calendar;
    TextView reporttimeanddate;
    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report_reminder);
        calendar=Calendar.getInstance();
        createnotificationchannel();
        reportname=findViewById(R.id.enter_rp);
        addReportReminder=findViewById(R.id.add_report_btn);
        reporttimeanddate=findViewById(R.id.rp_time_date);
        setTimeforrp=findViewById(R.id.select_time_rp);
        selectdate=findViewById(R.id.select_date_rp);
        setTimeforrp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showtimepicker();

            }
        });
        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal= Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(AddReportReminder.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        if(i1 != 0){

                            calendar.set(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth());
                            date=new Date(calendar.getTimeInMillis());
                            reporttimeanddate.setText(date.toString());

                        }
                    }
                },year,month,day);
                dialog.show();

            }
        });
        addReportReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long id = System.currentTimeMillis();
                ReportDB rdb = Room.databaseBuilder(getApplicationContext(), ReportDB.class, "personalreminder").allowMainThreadQueries().build();
                ReportDAO dao = rdb.reportDAO();
                dao.insertall(new ReportEntity(reporttimeanddate.getText().toString(), id, calendar.getTimeInMillis()));
                setalarm();
                Intent i = new Intent(AddReportReminder.this, ReposrtActivity.class);
                startActivity(i);
                finish();
            }
        });
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
    private void showtimepicker() {
        cal=Calendar.getInstance();
        int hours=cal.get(Calendar.HOUR_OF_DAY);
        int min=cal.get(Calendar.MINUTE);
        TimePickerDialog dialog=new TimePickerDialog(AddReportReminder.this, androidx.appcompat.R.style.Theme_AppCompat_DayNight_Dialog, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                calendar.set(Calendar.HOUR_OF_DAY,i);
                calendar.set(Calendar.MINUTE,i1);
                date=new Date(calendar.getTimeInMillis());
                reporttimeanddate.setText(date.toString());

            }
        },hours,min,false);

        dialog.show();

    }
    private void setalarm() {
        final int aid= (int) System.currentTimeMillis();
        manager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent=new Intent(AddReportReminder.this, reportreiciver.class);
        intent.putExtra("notificationname",reportname.getText().toString());
        intent.putExtra("id",calendar.getTimeInMillis());
        pendingIntent=PendingIntent.getBroadcast(this,aid,intent,PendingIntent.FLAG_MUTABLE);
        date=new Date(calendar.getTimeInMillis());
        Toast.makeText(this, date.toString(), Toast.LENGTH_SHORT).show();
        manager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

    }
}