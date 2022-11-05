package com.hebaelsaid.android.newsapp.presentation.main_ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.hebaelsaid.android.newsapp.R;
import com.hebaelsaid.android.newsapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
    }
}