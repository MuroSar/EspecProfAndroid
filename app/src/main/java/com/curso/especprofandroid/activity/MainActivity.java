package com.curso.especprofandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.curso.especprofandroid.R;

public class MainActivity extends AppCompatActivity {

    public static final String USER_ID = "USER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
