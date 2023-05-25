package com.example.medicinereminder.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicinereminder.R;

import java.util.regex.Pattern;

public class RegisterScreen extends AppCompatActivity {

    public static final String SHARED_PREFS="email";
//    public static final String SHARED_PREFS="email";
    public static final Pattern PASSWORD_PATTERN = Pattern.compile(".{6,}");
    Button Register;
    EditText password;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        password=findViewById(R.id.Rpwd);
        Register=findViewById(R.id.register);
        textView=findViewById(R.id.acc_login);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterScreen.this,LoginScreen.class);
                startActivity(intent);
                finish();
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
                Intent intent=new Intent(RegisterScreen.this,LoginScreen.class);
                startActivity(intent);
                finish();

            }
        });
    }
    public void register(){
        String email1="",pwd1="";

        if(password != null){

             pwd1 = password.getText().toString();
            Log.d("tag",pwd1);
        }
        if (!PASSWORD_PATTERN.matcher(pwd1).matches()) {
            Toast.makeText(this, "password must be 6 digit", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences prefs=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putString("password",pwd1);
        editor.apply();
        Toast.makeText(this, "password generated successfully", Toast.LENGTH_LONG).show();

    }
}