package com.fxz.writenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class SelectActivity extends Activity {

    //定义音乐播放器对象
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        if(MainActivity.isPlay){//如果主界面音乐播放状态变量为true
            playMusic();//播放背景音乐
        }

    }

    //播放背景音乐
    private void playMusic() {
        //创建音乐播放器对象并加载播放音乐文件
        mediaPlayer=MediaPlayer.create(this,R.raw.number_music);
        //设置音乐循环播放
        mediaPlayer.setLooping(true);
        //播放音乐
        mediaPlayer.start();
    }

    //该方法实现选择数字界面停止时，背景音乐停止
    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer!=null){//音乐播放器不为空时
            mediaPlayer.stop();//停止播放音乐
        }
    }

    //该方法实现数字选择界面销毁时，背景音乐停止并释放音乐资源
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){//音乐播放器不为空时
            mediaPlayer.stop();//停止播放音乐
            mediaPlayer.release();//释放音乐资源
            mediaPlayer=null;//设置音乐播放器为空
        }
    }

    //该方法实现从另一界面返回数字选择界面时，根据音乐播放状态播放音乐
    @Override
    protected void onRestart() {
        super.onRestart();
        if (MainActivity.isPlay){//如果音乐处于播放状态
            playMusic();
        }
    }

    //选择数字“1”后跳转到OneActivity
    public void onOne(View v){
        startActivity(new Intent(SelectActivity.this,OneActivity.class));
    }
}