package com.lucky.unittestlearn.autoinstrumenttest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lucky.unittestlearn.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zfz
 *         Created by zfz on 2018/2/24.
 */

public class LoadLocalDataActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "LoadLocalDataActivity";
    TextView tvContent;
    Button btnLoadData;
    SimpleIdlingResource idlingResource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaddata);
        tvContent = (TextView) findViewById(R.id.tv_load_data);
        btnLoadData = (Button) findViewById(R.id.btn_load_data);
        btnLoadData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_load_data){
            loadData();
        }
    }

    private void loadData(){
        idlingResource.setIdleState(false);
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(()-> {
            try {
                Thread.sleep(5*1000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvContent.setText("Data Load Finished");
                        idlingResource.setIdleState(true);
                        Log.e(TAG, "loadData: Task Finish");
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    @VisibleForTesting
    public IdlingResource getIdlingResource(){
        if (idlingResource == null){
            idlingResource = new SimpleIdlingResource();
        }
        return idlingResource;
    }


}
