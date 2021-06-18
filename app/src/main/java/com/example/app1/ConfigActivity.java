package com.example.app1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigActivity extends AppCompatActivity {

    private static final String TAG="ConfigActivity";
    EditText dollartext;
    EditText eurotext;
    EditText wontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);


    }
    public void save(View btn){



        Intent intent=getIntent();
        float dollar2=intent.getFloatExtra("dollar_key",0.0f);
        float euro2=intent.getFloatExtra("euro_key",0.0f);
        float won2=intent.getFloatExtra("won_key",0.0f);

        Log.i(TAG,"onCreate:ConfigActivity dollar2="+dollar2);
        Log.i(TAG,"onCreate:ConfigActivity euro2="+euro2);
        Log.i(TAG,"onCreate:ConfigActivity won2="+won2);

        //获取参数值
        dollartext=findViewById(R.id.dollar_rate);
        eurotext=findViewById(R.id.euro_rate);
        wontext=findViewById(R.id.won_rate);

        dollartext.setText(String.valueOf(dollar2));
        eurotext.setText(String.valueOf(euro2);
        wontext.setText(String.valueOf(won2));


    }




    public void save(View btn){
        Log.i(TAG,"save:");
        float newdollar=Float.parseFloat(dollartext.getText().toString());
        float neweuro=Float.parseFloat(eurotext.getText().toString());
        float newwon=Float.parseFloat(wontext.getText().toString());

        Log.i(TAG,"save: newdollar="+newdollar);
        Log.i(TAG,"save: neweuro="+neweuro);
        Log.i(TAG,"save: newwon="+newwon);

        Intent ret=getIntent();
        Bundle bd1=new Bundle();
        bd1.putFloat("dollar_key",newdollar);
        bd1.putFloat("euro_key",neweuro);
        bd1.putFloat("won_key",newwon);
        ret.putExtras(bd1);
        setResult(1,ret);

        finish();
    }



}


