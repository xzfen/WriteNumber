package com.fxz.writenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Timer timer=new Timer();//创建Timer对象，用于设置启动界面显示的时间
        //创建TimerTask对象，用于实现启动界面与游戏主界面的跳转
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                //从启动界面跳转到游戏主界面
                startActivity(new Intent(StartActivity.this,MainActivity.class));
                //关闭启动界面
                finish();
            }
        };
        //设置显示启动界面2秒后，跳转游戏主界面
        timer.schedule(timerTask,2000);
    }
}