package com.lucky.unittestlearn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * @author zfz
 * Created by zfz on 2017/2/24.
 */

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        String textPassBy=getIntent().getStringExtra(SecondActivity.TEXT_TO_BE_SEND);
        TextView textView=(TextView)findViewById(R.id.textinput);
        textView.setText(textPassBy);
    }
}
