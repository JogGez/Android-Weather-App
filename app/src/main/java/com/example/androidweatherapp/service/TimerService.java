package com.example.androidweatherapp.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class TimerService extends IntentService {
    private static Timer timer = new Timer();
    ResultReceiver myResultReceiver;
    private static String TAG = CountdownResultReceiver.class.getSimpleName();

    public TimerService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent!=null){
            myResultReceiver =  intent.getParcelableExtra("result");
        }
        startService();
    }

    private void startService()
    {
        timer.scheduleAtFixedRate(new mainTask(), 1000000, 100000);
    }

    private class mainTask extends TimerTask
    {
        @Override
        public void run()
        {
            Bundle bundle = new Bundle();
            bundle.putString("compValue", "Complete");
            myResultReceiver.send(100,bundle);
        }
    }

    public void onDestroy()
    {
        super.onDestroy();

    }


}
