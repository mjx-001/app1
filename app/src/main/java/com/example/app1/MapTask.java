package com.example.app1;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.util.HashMap;

public class MapTask<msg> {

    private static final String TAG = "MapTask";
    try
    {
        Thread.sleep(3000);
        Document doc = Jsoup.connect().get();
        Log.i(TAG,"run : "+ doc.title());
        Element table = doc.getElementsByTag("table").first();

        Elements trs = tables.getElementsByTag("tr");
        for (Element tr : trs){
            Element tds = tr.getElementsByTag("td");
            if (tds.size()>=5) {
                String str = tds.get(0).text();
                String val = tds.get(5).text();
                Log.i(TAG,"run: " + str + "->" + val);
                HashMap<String,String> map = new HashMap<String, String>();
                map.put("ItemTitle",str);
                map.put("ItemDetail",val);
                ret.add(map);
            }
        }
    }catch(IOException | InterruptedException e){
        e.printStackTrace();
    }

    Handler handler;
    Message msg = handler.obtainMessage(9,ret);
    handler.sendMessage(msg);




}

