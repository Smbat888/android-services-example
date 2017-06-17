package com.example.smbat_s.broadcastreceiverexample;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LocalWordService extends Service {

    private final IBinder mBinder = new MyBinder();
    private List<String> mResultList = new ArrayList<>();
    private int mCounter = 1;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        addResultValues();
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        addResultValues();
        return mBinder;
    }

    public class MyBinder extends Binder {
        LocalWordService getService() {
            return LocalWordService.this;
        }
    }

    public List<String> getWordList() {
        return mResultList;
    }

    private void addResultValues() {
        Random random = new Random();
        final List<String> input = Arrays.asList("Linux", "Android","iPhone","Windows7" );
        mResultList.add(input.get(random.nextInt(3)) + " " + mCounter++);
        if (mCounter == Integer.MAX_VALUE) {
            mCounter = 0;
        }
    }
}
