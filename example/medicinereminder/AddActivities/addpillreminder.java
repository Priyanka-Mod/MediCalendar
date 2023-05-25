package com.example.medicinereminder.AddActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.medicinereminder.Activities.MedicineActivity;
import com.example.medicinereminder.R;
import com.example.medicinereminder.RDBs.medicineRDB.MedicineDAO;
import com.example.medicinereminder.RDBs.medicineRDB.MedicineDB;
import com.example.medicinereminder.RDBs.medicineRDB.MedicineEntity;
import com.example.medicinereminder.recivers.medicinereciver;
import com.example.medicinereminder.recivers.reportreiciver;

import java.util.Calendar;
import java.util.Date;

public class addpillreminder extends AppCompatActivity {
    String []repretation={"Only morning","Morning and night","3 times a day"};
    EditText pillnameET;
    Button setmedicineReminder;
    AutoCompleteTextView repeattimes;
    private Calendar cal,calendar;
    Date date;
    AlarmManager manager;
    PendingIntent pendingIntent;
    Button Psettime,Addreminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpillreminder);
        calendar=Calendar.getInstance();
        pillnameET=findViewById(R.id.pillname);
        Psettime=findViewById(R.id.selecttimeforpill);
//        Addreminder=findViewById(R.id.setpillrem);
        setmedicineReminder=findViewById(R.id.setpillrem);
        setmedicineReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add into database
                MedicineDB db= Room.databaseBuilder(getApplicationContext(), MedicineDB.class,"pillreminder").allowMainThreadQueries().build();
                MedicineDAO dao=db.medicineDAO();
                dao.insertall(new MedicineEntity(pillnameET.getText().toString(),System.currentTimeMillis(),0));
                startActivity(new Intent(addpillreminder.this, MedicineActivity.class));
                setalarm();
                finish();
            }
        });
        Psettime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showtimepicker();
            }
        });

    }
    private void showtimepicker() {
        cal= Calendar.getInstance();
        int hours=cal.get(Calendar.HOUR_OF_DAY);
        int min=cal.get(Calendar.MINUTE);
        TimePickerDialog dialog=new TimePickerDialog(addpillreminder.this, androidx.appcompat.R.style.Theme_AppCompat_DayNight_Dialog, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                calendar.set(Calendar.HOUR_OF_DAY,i);
                calendar.set(Calendar.MINUTE,i1);
                date=new Date(calendar.getTimeInMillis());
//                showtd.setText(date.toString());

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

        Intent intent=new Intent(addpillreminder.this, medicinereciver.class);
        intent.putExtra("notificationname",pillnameET.getText().toString());
        intent.putExtra("id",calendar.getTimeInMillis());
        pendingIntent= PendingIntent.getBroadcast(this,aid,intent,PendingIntent.FLAG_MUTABLE);
        date=new Date(calendar.getTimeInMillis());
        Toast.makeText(this, date.toString(), Toast.LENGTH_SHORT).show();
        manager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

    }
}