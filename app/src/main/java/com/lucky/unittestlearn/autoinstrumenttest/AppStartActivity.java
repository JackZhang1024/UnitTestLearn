package com.lucky.unittestlearn.autoinstrumenttest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lucky.unittestlearn.R;

/**
 * Created by zfz on 2017/3/15.
 * Android自动化测试Demo
 */

public class AppStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument);
    }
}
