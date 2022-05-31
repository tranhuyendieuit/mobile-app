package com.example.nikestoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        email = findViewById(R.id.edt_email_register);
        pass = findViewById(R.id.edt_password_register);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
        },2000);
    }

    private void nextActivity() {
        FirebaseUser firebaseUser   = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser == null){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}