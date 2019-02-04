package com.example.alarm.dummy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alarm.BroadcastReceiverForAlarm;
import com.example.alarm.R;
import com.example.alarm.ServiceForAlarm;


public class Test extends AppCompatActivity implements View.OnClickListener {
    private BroadcastReceiverForAlarm alarm;
    EditText editTextTime;
    EditText editTextCode;
    Button editButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_test);

        editTextTime = findViewById(R.id.editText);
        editTextCode = findViewById(R.id.editText2);
        editButton = findViewById(R.id.edit);
        editButton.setOnClickListener(this);


        alarm=new BroadcastReceiverForAlarm();
        /*
       PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock mWakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "MyTag: this");
        mWakeLock.acquire();*/

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

    }
    public void startRepeatingTimer(View view){
finish();
    }

    public void cancelRepeatingTimer(View view){
        Context context= this.getApplicationContext();
        alarm.CancelAlarm(context);
    }


    public void onetimeTimer(View view){
        Context context= this.getApplicationContext();
        if(alarm!=null){
            alarm.setOnetimeTimer(context);
            Toast.makeText(this, "44", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit:
                String codeStr = editTextCode.getText().toString();
                int codeInt = Integer.parseInt(codeStr);
                BroadcastReceiverForAlarm.requestCode = codeInt;
                String timeStr = editTextTime.getText().toString();
                int timeInt = Integer.parseInt(timeStr);
                BroadcastReceiverForAlarm.value = timeInt;
                Toast.makeText(this, "" + BroadcastReceiverForAlarm.requestCode + BroadcastReceiverForAlarm.value , Toast.LENGTH_SHORT).show();
        }
    }
}
