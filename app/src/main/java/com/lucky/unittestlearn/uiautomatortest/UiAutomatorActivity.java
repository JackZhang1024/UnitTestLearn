package com.lucky.unittestlearn.uiautomatortest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lucky.unittestlearn.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zfz
 * Created by zfz on 2018/2/23.
 */

public class UiAutomatorActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etInput1, etInput2;
    private TextView tvResult;
    private Button btnCalculate;
    private RecyclerView rvList;
    private MyRecyclerAdapter myRecyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uiautomator);
        etInput1 = (EditText) findViewById(R.id.et_input_1);
        etInput2 = (EditText) findViewById(R.id.et_input_2);
        tvResult = (TextView) findViewById(R.id.tv_result);
        btnCalculate = (Button) findViewById(R.id.btn_calculate);
        rvList = (RecyclerView) findViewById(R.id.rv);
        btnCalculate.setOnClickListener(this);
        myRecyclerAdapter = new MyRecyclerAdapter(createUsers());
        rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(myRecyclerAdapter);
        myRecyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(String item) {
                showDialog(item);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_calculate:
                String strInput1 = etInput1.getText().toString().trim();
                String strInput2 = etInput2.getText().toString().trim();
                int result = Integer.parseInt(strInput1)+Integer.parseInt(strInput2);
                tvResult.setText(String.valueOf(result));
                break;
            default:

                break;
        }
    }

    private void showDialog(String message){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("UiAutomator测试")
                .setMessage(String.format("信息: %s", message))
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
        private List<String> users = null;
        public MyRecyclerAdapter(List<String> users) {
            this.users = users;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
            return new MyRecyclerViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyRecyclerViewHolder viewHolder = (MyRecyclerViewHolder) holder;
            viewHolder.setItemData(users.get(position));
            viewHolder.itemView.findViewById(R.id.tv_user).setTag(position);
            viewHolder.itemView.findViewById(R.id.tv_user).setOnClickListener(this);
        }

        @Override
        public int getItemCount() {
            return users.size();
        }

        @Override
        public void onClick(View v) {
            int position = (int)v.getTag();
            if (onItemClickListener!=null){
                onItemClickListener.onItemClick(users.get(position));
            }
        }

        private OnItemClickListener onItemClickListener;

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }
    }


    interface OnItemClickListener {
        /**
         * 点击Item 返回用户信息并弹框
         * @param item  dd
         */
        void onItemClick(String item);
    }


    class MyRecyclerViewHolder extends RecyclerView.ViewHolder{
        private TextView tvUserName;

        public MyRecyclerViewHolder(View itemView) {
            super(itemView);
            tvUserName = (TextView) itemView.findViewById(R.id.tv_user);
        }

        public void setItemData(String user){
            tvUserName.setText(user);
        }
    }


    private List<String> createUsers(){
        List<String> users = new ArrayList<>();
        int size = 100;
        for (int i =0; i < size; i++){
             users.add("玩家 "+i);
        }
        return users;
    }

}
