package com.ertugrulkoc.drawsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChandelierRoot switchView = new ChandelierRoot(this);
        setContentView(switchView);

    }
}