package com.example.app2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app1.R;

public class MyApp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}


//大致思路：主体为汇率计算，略微涉及一些小知识点：
//1、页面布局；2、参数传递，数据返回处理；3、汇率数据保存；4、多线程消息传递（Handler）；5、解析汇率数据(Jsoup下载！！！);
//（数据来源于课程案例里所举的两个）6、添加菜单及手机基本内容（如翻转）；






