package com.lucky.unittestlearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by zfz on 2017/2/24.
 */

public class SecondActivity extends AppCompatActivity  {

    private TextView mTextView;
    private EditText mEditText;
    public static final String TEXT_TO_BE_SEND = "TEXT_TO_BE_SEND";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mEditText = (EditText) findViewById(R.id.editTextUserInput);
        mTextView = (TextView) findViewById(R.id.textToBeChanged);
        findViewById(R.id.changeTextBt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String text = mEditText.getText().toString();
                mTextView.setText(text);
            }
        });
        findViewById(R.id.activityChangeTextBtn).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final String text = mEditText.getText().toString();
                Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                intent.putExtra(TEXT_TO_BE_SEND,text);
                startActivity(intent);
            }
        });
    }

}
