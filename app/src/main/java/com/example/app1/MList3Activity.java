package com.example.app1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

package com.example.app1;

public class MList3Activity extends AppCompatActivity.OnItemClickListener{


    private static final String TAG = "MyAdapter";
    ListView listView3;
    RateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_list3);
        ListView listview3  = findViewById(R.id.mylist3);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        listview3.setOnItemClickListener();


       //progressBar.setVisibility(View.GONE);
        //listview3.setVisibility(View.VISIBLE);

        ArrayList<HashMap<String,String>> listItems = new ArrayList<HashMap<String,String>>();
        for (int i = 0;i<10;i++){
            HashMap<String,String> map = new HashMap<String, String>();
            map.put("ItemTitle","Rate: " + i );
            map.put("ItemDetail","detail" + i);
            listItems.add(map);
        }
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItems,
                R.layout.list_item,new String[]{"ItemTitle","ItemDetail"},
                new int[]{R.id.itemTitle,R.id.itemDetail}
                );
        listview3.setAdapter(listItemAdapter);



        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg){
            if (msg.what==9){
                ArrayList<HashMap<String,String>> retlist = (ArrayList<HashMap<String,String>>)msg.obj;
                SimpleAdapter listItemAdapter = new SimpleAdapter(MList3Activity.this,
                        retlist,
                        R.layout.list_item,
                        new String[]{"ItemTitle"."ItemDetail"},
                        new int[]{R.id.itemTitle,R.id.itemDetail});
                listview3.setAdapter(listItemAdapter);
                Toast.makeText(MList3Activity.this,"Update Over",Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                listview3.setVisibility(View.VISIBLE);
            }
            super.handleMessage(msg);
            }
        };
        MapTask task = new MapTask();
        task.setHandler(handler);
        Thread t = new Thread((Runnable) task);
        t.start();

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,long id){
            Log.i(TAG,"onItemClick: position=" + position);
            Rate rate = (Rate) listview3.getItemAtPosition(position);
            //adapter.remove(rate);
        }




        @Override
        public void onItemClick(AdapterView<?> parent,View view,int position,long id){
            Log.i(TAG,"onItemClick: position=" + position);
            Object itemAtPosition = listview3.getItemAtPosition(position);
            HashMap<String,String> map = (HashMap<String,String>) itemAtPosition;
            String titleStr = map.get("ItemTitle");
            String detailStr = map.get("ItemDetail");
            Log.i(TAG,"onItemClick:titleStr=" + titleStr);
            Log.i(TAG,"onItemClick:detailStr=" + detailStr);
        }

        Intent calcIntent = new Intent(this,CalcActivity.class);
        calcIntent.putExtra("title",titleStr);
        calcIntent.putExtra("rate",detailStr);

        @Override
        public boolean onItemLongClick(AdapterView<?> parent,View view,int position,long id) {
            Log.i(TAG,"onItemLongClick: 长按操作");
            Rate rate = (Rate) listview3.getItemAtPosition(position);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示")
                        .setMessage("请确认是否删除当前数据")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i(TAG,"onClick : 有对话框事件处理");
                                adapter.remove(rate);

                            }
                        }).setNegativeButton("否",null);
                builder.create().show();

                return true;
        }
















}