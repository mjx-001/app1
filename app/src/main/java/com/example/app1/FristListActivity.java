package com.example.app1;

import android.app.ListActivity;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;





import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FristListActivity<handler> extends ListActivity implements Runnable{

    Handler handler;
    private static final String TAG = "FristListActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        List<String> list1 = new ArrayList<String>();
        for (int i=1;i<100;i++){
            list1.add("item"+i);
        }
        //String[] list_data = {"one","two","three","four"};
        ListAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,list1);
        setListAdapter(adapter);
    }

    handler = new  Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg){
            if (msg.what==9){
                List<String>retList = (ArrayList<String>)msg.obj;
                ListAdapter adapter = new ArrayAdapter<String>(
                        FristListActivity.this,
                        android.R.layout.simple_list_item_1,retList);
                setListAdapter(adapter);
                Toast.makeText(FristListActivity.this,"Update over",Toast.LENGTH_SHORT).show();
            }

        }
        RateActivity r = new RateActivity();
        r.setHandler(handler);
        Thread t = new Thread(r);
        t.start();
    };

    @Override
    public void run() {
        Log.i(TAG,"run:.....");
    }

    Bundle bd1 = new Bundle();
    try

    {
        Document doc = Jsoup.connect("https://www.boc.cn/sourcedb/whpj/").get();
        Log.i(TAG, "run:" + doc.title());
        Element tables = doc.getElementsByTag("table").first();
        //按行取
        Element trs = tables.getElementsByTag("tr");
        for (Element tr : trs) {
            Element tds = tr.getElementsByTag("td");
            if (tds.size() >= 5) {
                String str = tds.get(0).text();
                String val = tds.get(5).text();
                Log.i(TAG, "run: " + str + "->" + val);
                if ("美元".equals(str)) {
                    float r = 100 / Float.parseFloat(val);
                    bd1.putFloat("dollar", r);
                } else if ("欧元".equals(str)) {
                    float r = 100 / Float.parseFloat(val);
                    bd1.putFloat("euro", r);
                } else if ("韩元".equals(str)) {
                    float r = 100 / Float.parseFloat(val);
                    bd1.putFloat("won", r);
                }
            }
        }

    }catch(IOException e){
        e.printStackTrace();
    }

    Message msg = handler.obtainMessage(9,retList);
    handler.sendMessage(msg);





}

