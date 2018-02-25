package com.lucky.unittestlearn.autoinstrumenttest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lucky.unittestlearn.R;

/**
 * Created by zfz on 2017/3/15.
 */

public class EspressoTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espresso);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EspressoTestActivity.this,"EspressoTestActivity !",Toast.LENGTH_LONG).show();
            }
        });
    }
}
