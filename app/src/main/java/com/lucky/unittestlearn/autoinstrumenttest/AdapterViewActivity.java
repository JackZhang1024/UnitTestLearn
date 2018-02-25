package com.lucky.unittestlearn.autoinstrumenttest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.lucky.unittestlearn.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zfz
 * Created by zfz on 2017/3/15.
 */

public class AdapterViewActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapterview);
        mListView=(ListView) findViewById(R.id.listview);
        mListView.setAdapter(new ArrayAdapter<SearchItem>(this,R.layout.list_item,R.id.textview,createSearchItems()));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AdapterViewActivity.this,"id "+id,Toast.LENGTH_LONG).show();
            }
        });
    }

    public List<SearchItem> createSearchItems(){
        List<SearchItem> searchItems=new ArrayList<>();
        for(int index=0;index<100;index++){
            SearchItem searchItem=new SearchItem();
            searchItem.setKeyword("Teacher "+index);
            searchItems.add(searchItem);
        }
        return searchItems;
    }



}
