package com.lzr.test222;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private LoadingWaveView mWaveView;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private boolean isRunning;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            for (int i = 0; i < 10; i++) {
                int count = new Random().nextInt(160) + 20;
                mWaveView.addData((short) count);
            }
            return false;
        }
    });
    private RelativeLayout mLoadingLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWaveView = findViewById(R.id.wave_view);
        mLoadingLayout = findViewById(R.id.loading_layout);

    }


    public void createtu(View view) {
        if (isRunning) {

            mTimer.cancel();
            mTimer.purge();
            mTimer = null;
            mTimerTask.cancel();
            isRunning = false;
            mLoadingLayout.setVisibility(View.GONE);
        }else {
            mTimer = new Timer();
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    mHandler.sendEmptyMessage(0);
                    Log.e("lzr","活动中。。。。。。。。");
                }
            };
            mTimer.schedule(mTimerTask, 0, 150);
            isRunning = true;
            mLoadingLayout.setVisibility(View.VISIBLE);
        }
    }
}
