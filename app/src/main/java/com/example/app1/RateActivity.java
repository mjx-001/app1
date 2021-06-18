package com.example.app1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;



public class RateActivity extends AppCompatActivity implements Runnable {


    Bundle bd1 = new Bundle();
    private static final String TAG="Rate";
    float dollarRate=0.2f;
    float euroRate=0.4f;
    float wonRate=0.6f;

    EditText rmb;
    TextView show;

    Handler handler;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rate);


        //读取保存的数据
        SharedPreferences sharedPreferences = getSharedPreferences("my_rate", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        dollarRate = sharedPreferences.getFloat("dollar_rate", 0.0f);
        euroRate = sharedPreferences.getFloat("euro_rate", 0.0f);
        wonRate = sharedPreferences.getFloat("won_rate", 0.0f);

        //更新数据
        editor.putFloat("dollar_rate", dollarRate);
        editor.putFloat("Euro_rate", euroRate);
        editor.putFloat("Won_rate", wonRate);
        editor.commit();
        Log.i(TAG, "onActivityResult: 数据已保存");


        Log.i(TAG, "onCreate:dollarRate=" + dollarRate);
        Log.i(TAG, "onCreate:euroRate=" + euroRate);
        Log.i(TAG, "onCreate:wonRate=" + wonRate);

        //保存更新日期
        SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat("dollar_rate",dollarRate);
        editor.putFloat("euro_rate",euroRate);
        editor.putFloat("won_rate",wonRate);
        editor.putString("update_date",todayStr);
        editor.apply();


        //开启子线程
        Thread t = new Thread(this);
        t.start();

        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 5) {
                    String str = (String) msg.obj;
                    Log.i(TAG, "handleMessage: getMessage msg = " + str);
                    show.setText(str);
                }
                //收到消息的处理
                //Log.i(TAG,"handleMessage:收到消息"+msg.what);
                //if(msg.what==7)
                //  String str = (String)msg.obj;
                //Log.i(TAG,"handleMessage:OO");
                //TextView result = findViewById(R.layout)
                //result.setText(str);

                super.handleMessage(msg);
            }
        };

        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_rate);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }, 2000);
        }


        //处理返回数据
        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
            if (requestCode == 3 && requestCode == 1) {
                Bundle bundle = data.getExtras();
                dollarRate = bundle.getFloat("dollar_key", 0.0f);
                euroRate = bundle.getFloat("euro_key", 0.0f);
                wonRate = bundle.getFloat("won_key", 0.0f);

                Log.i(TAG, "onActivityResult:dollarRate=" + dollarRate);
                Log.i(TAG, "onActivityResult:euroRate=" + euroRate);
                Log.i(TAG, "onActivityResult:wonRate=" + wonRate);
            }
            super.onActivityResult(requestCode, resultCode, data);
        }

        //Jsoup
        //implementation 'org.jsoup:jsoup:1.11.3'


            @Override
            protected void onSavedInsTanceState (Bundle outState){
                super.onSaveInstanceState(outstate);
                string scorea = ((TextView) findViewById(R.id.score)).getText().toString();
                string scoreb = ((TextView) findViewById(R.id.score2)).getText().toString();

                Log.i(TAG, "onSaveInstanceState: ");
                outState.putString("team_score", scorea);
                outState.putString("team_score", scoreb);
            }

            @Override
            protected void onRestoreInstanceState (Bundle savedInstanceState){
                super.onRestoreInstanceState(SavedInsTanceState);
                String scorea = SavedInsTanceState.getString("teama_score");
                String scoreb = SavedInsTanceState.getString("teamb_score");
                Log.i(TAG, "onRestoreInstanceState: ");
                ((TextView) findViewById(R.id.score)).setText(scorea);
                ((TextView) findVde 'wiewById(R.id.score2)).setText(scoreb);
            }


        }



    }
    @Override
    public void run() {
        Log.i(TAG, "run:run().....");
        for(int i=1;i<=3;i++){
            Log.i(TAG, "run: i=" + i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Message msg = handler.obtainMessage(5);
        msg.obj = "Hello from run()";
        handler.sendMessage(msg);

        URL url = null;
        try {
            url = new URL ("www.usd-cny.com/bankofchina.htm");
            HttpURLConnection http = (HttpsURLConnection)url.openConnection();
            InputStream in =  http.getInputStream();

            String html = inputStreamString(in);
            Log.i(TAG,"run:html="+html);

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        //解析汇率
        Document doc = Jsoup.parse(html);
        Bundle bundle = new Bundle();
        Document doc = null;
        try {
            String url = "http://www.usd-cny.com/bankofchina.htm";
            doc = Jsoup.connect(url).get();
            Log.i(TAG, "run: " + doc.title());
            Elements tables = doc.getElementsByTag("table");

            Element table6 = tables.get(5);
            Elements tds = table6.getElementsByTag("td");
            for(int i=0;i<tds.size();i+=8){
                Element td1 = tds.get(i);
                Element td2 = tds.get(i+5);

                String str1 = td1.text();
                String val = td2.text();

                Log.i(TAG, "run: " + str1 + "==>" + val);

                float v = 100f / Float.parseFloat(val);
                if("美元".equals(str1)){
                    bundle.putFloat("dollar-rate", v);
                }else if("欧元".equals(str1)){
                    bundle.putFloat("euro-rate", v);
                }else if("韩元".equals(str1)){
                    bundle.putFloat("won-rate", v);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回消息给主线程
        Message msg = handler.obtainMessage(5);
        msg.obj = bundle;
        handler.sendMessage(msg);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==5){
                    Bundle bdl = (Bundle) msg.obj;
                    dollarRate = bdl.getFloat("dollar-rate");
                    euroRate = bdl.getFloat("euro-rate");
                    wonRate = bdl.getFloat("won-rate");

                    Log.i(TAG, "handleMessage: dollarRate:" + dollarRate);
                    Log.i(TAG, "handleMessage: euroRate:" + euroRate);
                    Log.i(TAG, "handleMessage: wonRate:" + wonRate);
                    Toast.makeText(RateActivity.this, "汇率已更新", Toast.LENGTH_SHORT).show();
                }
                super.handleMessage(msg);
            }
        };

    }


    public void open(View btn){
        Intent config = new Intent(this,ConfigActivity.class);
        config.putExtra("dollar_rate_key",dollarRate);
        config.putExtra("euro_rate_key",euroRate);
        config.putExtra("won_rate_key",wonRate);

        Log.i(TAG, "open: dollarRate=" + dollarRate);
        Log.i(TAG, "open: euroRate=" + euroRate);
        Log.i(TAG, "open: wonRate=" + wonRate);

        startActivityForResult(config,3);
    }
}














