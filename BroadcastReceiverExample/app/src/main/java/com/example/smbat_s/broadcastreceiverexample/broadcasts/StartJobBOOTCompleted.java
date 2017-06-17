package com.example.smbat_s.broadcastreceiverexample.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.smbat_s.broadcastreceiverexample.Utils;

public class StartJobBOOTCompleted extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Utils.scheduleJob(context);
    }
}
