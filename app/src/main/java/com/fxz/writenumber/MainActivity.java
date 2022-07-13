package com.fxz.writenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    static boolean isPlay=true;//记录音乐播放状态变量
    MediaPlayer mediaPlayer;//定义音乐播放器对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btn_play = findViewById(R.id.btn_play);//获取btn_play按钮
        //为btn_play按钮设置按键事件监听器
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当前界面跳转至选择数字界面
                startActivity(new Intent(MainActivity.this, SelectActivity.class));
            }
        });
        ImageButton btn_about = findViewById(R.id.btn_about);//获取btn_about按钮
        //为btn_about按钮设置按键事件监听器
        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当前界面跳转至关于界面
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });

        ImageButton btn_music = findViewById(R.id.btn_music);//获取播放/停止音乐播放按键
        playMusic();//调用播放音乐的方法
        //为btn_music按键设置按键监听事件
        btn_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //音乐播放时单击按钮停止音乐播放，音乐停止时单击按钮播放音乐
                if(isPlay){//如果音乐在播放状态
                    if(mediaPlayer!=null){//音乐播放器不为空时
                        mediaPlayer.stop();//停止音乐
                        ///切换按钮图标为停止状态背景图片
                        btn_music.setBackgroundResource(R.drawable.btn_music2);
                        //设置音乐处于停止状态
                        isPlay=false;
                    }
                }else{
                    //调用播放音乐的方法
                    playMusic();
                    //切换按钮图标为播放状态图片
                    btn_music.setBackgroundResource(R.drawable.btn_music1);
                    //设置音乐处于播放状态
                    isPlay=true;
                }
            }
        });
    }

    //播放音乐的方法
    private void playMusic() {
        //创建音乐播放器对象并加载播放音乐文件
        mediaPlayer=MediaPlayer.create(this,R.raw.main_music);
        //设置音乐循环播放
        mediaPlayer.setLooping(true);
        //播放音乐
        mediaPlayer.start();
    }

    //该方法实现游戏主界面停止时，背景音乐停止
    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer!=null){//音乐播放器不为空时
            mediaPlayer.stop();//停止播放音乐
        }
    }

    //该方法实现游戏主界面销毁时，背景音乐停止并释放音乐资源
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){//音乐播放器不为空时
            mediaPlayer.stop();//停止播放音乐
            mediaPlayer.release();//释放音乐资源
            mediaPlayer=null;//设置音乐播放器为空
        }
    }

    //该方法实现从另一界面返回游戏主界面时，根据音乐播放状态播放音乐
    @Override
    protected void onRestart() {
        super.onRestart();
        if (isPlay){//如果音乐处于播放状态
            playMusic();
        }
    }
}