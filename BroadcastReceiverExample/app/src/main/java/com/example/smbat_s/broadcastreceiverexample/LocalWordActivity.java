package com.example.smbat_s.broadcastreceiverexample;

import android.app.Activity;
import android.os.Bundle;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LocalWordActivity extends Activity implements ServiceConnection {

    private LocalWordService mLocalWordService;
    private ArrayAdapter<String> mItemsAdapter;
    private List<String> mWordList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_word);
        mWordList = new ArrayList<>();
        mItemsAdapter = new ArrayAdapter<>(this, R.layout.simple_list_item,
                R.id.list_text, mWordList);
        final ListView list = (ListView) findViewById(R.id.simpleListView);
        list.setAdapter(mItemsAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, LocalWordService.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.updateList:
                if (mLocalWordService != null) {
                    Toast.makeText(this, "Number of elements" + mLocalWordService.getWordList().size(),
                            Toast.LENGTH_SHORT).show();
                    mWordList.clear();
                    mWordList.addAll(mLocalWordService.getWordList());
                    mItemsAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.triggerServiceUpdate:
                Intent service = new Intent(getApplicationContext(), LocalWordService.class);
                getApplicationContext().startService(service);
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        LocalWordService.MyBinder b = (LocalWordService.MyBinder) binder;
        mLocalWordService = b.getService();
        Toast.makeText(LocalWordActivity.this, "Connected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mLocalWordService = null;
    }
}
