package com.example.medicinereminder.Activities;
import static com.example.medicinereminder.Activities.RegisterScreen.SHARED_PREFS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicinereminder.R;

public class LoginScreen extends AppCompatActivity {
    Button Login;

    EditText passord;
    boolean state;
    TextView textView1;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        prefs=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        textView1=findViewById(R.id.new_register);
        Login=findViewById(R.id.login);
        passord=findViewById(R.id.pwd);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginScreen.this,RegisterScreen.class);
                startActivity(intent);
                finish();
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwd1 = passord.getText().toString();
                if( pwd1.equals(prefs.getString("password","pass"))){
                    Intent intent=new Intent(LoginScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginScreen.this, "invalid password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}