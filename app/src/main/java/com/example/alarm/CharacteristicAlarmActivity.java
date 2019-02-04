package com.example.alarm;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;

import android.transition.Scene;

import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;


public class CharacteristicAlarmActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int CONST_TIMEPICKER = 1;
   ViewGroup baseLayout;
    CardView cardView0, cardView1,cardView2, cardView3;
    Scene sceneA,sceneB, sceneC, sceneD;
    CheckBox cbRepeatShift,cbNoRepeat, cbRepeatEveryDay, cbRepeatDayOfMonth, cbRepeatDayOfWeek, cbManualSetting;
    TransitionSet set;
    TextView tvTimeSet, tvNumberAlarm;
    Integer minutes, hours;
    String mtime;
    long differenceTimeMills;
    FloatingActionButton fbSaveAlarm;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characteristic_alarm);

        baseLayout = (ViewGroup) findViewById(R.id.rootscena);
        sceneA = Scene.getSceneForLayout(baseLayout, R.layout.scena1, CharacteristicAlarmActivity.this);
        sceneB = Scene.getSceneForLayout(baseLayout, R.layout.scena2, CharacteristicAlarmActivity.this);
        sceneC = Scene.getSceneForLayout(baseLayout, R.layout.scena3, CharacteristicAlarmActivity.this);
        sceneD = Scene.getSceneForLayout(baseLayout, R.layout.scena4, CharacteristicAlarmActivity.this);
        mSetActionForScena();
        sceneA.enter();

        set = new TransitionSet();




    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {return;}
        mtime = data.getStringExtra("data");
        tvTimeSet.setText(mtime);
minutes = data.getIntExtra("minutes", 00);
hours = data.getIntExtra("hours",0);
AlarmData.ArrayAlarmData.get(position).setTimeAlarm(hours,minutes,mtime);

    }

    @Override
    public void onClick(View v) {

        Toast.makeText(this, ""+hours+minutes, Toast.LENGTH_SHORT).show();
        enabledForCheckBox(v);
switch (v.getId()){
    case R.id.cardview10:
        TransitionManager.go(sceneA,set);

        break;
    case R.id.cardview11:
        TransitionManager.go(sceneB,set);
        break;
    case R.id.cardview12:
        TransitionManager.go(sceneC,set);

        break;
    case R.id.cardview13:
        TransitionManager.go(sceneD,set);
        break;
    case R.id.tvTimeSet:
        Intent intent = new Intent(CharacteristicAlarmActivity.this, TimePickerActivity.class);
        startActivityForResult(intent,CONST_TIMEPICKER);
        break;
    case R.id.fbSaveAlarm:
        saveAlarm();
}





    }


    public void updateButtonListener(){
        cardView0 = findViewById(R.id.cardview10);
        cardView1 = findViewById(R.id.cardview11);
        cardView2 = findViewById(R.id.cardview12);
        cardView3 = findViewById(R.id.cardview13);
        tvTimeSet = findViewById(R.id.tvTimeSet); //время нужно на всех страницах
        tvTimeSet.setText(mtime);
        fbSaveAlarm = findViewById(R.id.fbSaveAlarm);
        fbSaveAlarm.setOnClickListener(CharacteristicAlarmActivity.this);
        tvNumberAlarm = findViewById(R.id.tvNumberAlarm);
        tvTimeSet.setText(AlarmData.ArrayAlarmData.get(position).getTimeAlarm());


        Intent intent = getIntent();
        position = intent.getIntExtra("position", 2);
        tvNumberAlarm.setText("" + (position+1));
    }
    private void mSetActionForScena(){
        sceneA.setEnterAction(new Runnable() {
            @Override
            public void run() {
                updateButtonListener();
                cardView1.setOnClickListener(CharacteristicAlarmActivity.this);
                cardView2.setOnClickListener(CharacteristicAlarmActivity.this);
                cardView3.setOnClickListener(CharacteristicAlarmActivity.this);

                tvTimeSet.setOnClickListener(CharacteristicAlarmActivity.this);
            }

        });
        sceneB.setEnterAction(new Runnable() {
            @Override
            public void run() {
                updateButtonListener();
                cardView0.setOnClickListener(CharacteristicAlarmActivity.this);
                cardView2.setOnClickListener(CharacteristicAlarmActivity.this);
                cardView3.setOnClickListener(CharacteristicAlarmActivity.this);

                cbRepeatEveryDay = findViewById(R.id.cbRepeatEveryDay);
                cbRepeatDayOfMonth = findViewById(R.id.cbRepeatDayOfMonth);
                cbRepeatDayOfWeek = findViewById(R.id.cbRepeatDayOfWeek);
                cbNoRepeat = findViewById(R.id.cbNoRepeat);
                cbRepeatShift = findViewById(R.id.cbRepeatShift);
                cbManualSetting = findViewById(R.id.cbManualSetting);

                cbNoRepeat.setOnClickListener(CharacteristicAlarmActivity.this);
                cbRepeatEveryDay.setOnClickListener(CharacteristicAlarmActivity.this);
                cbRepeatDayOfMonth.setOnClickListener(CharacteristicAlarmActivity.this);
                cbRepeatDayOfWeek.setOnClickListener(CharacteristicAlarmActivity.this);
                cbRepeatShift.setOnClickListener(CharacteristicAlarmActivity.this);
                cbManualSetting.setOnClickListener(CharacteristicAlarmActivity.this);
            }
        });
        sceneC.setEnterAction(new Runnable() {
            @Override
            public void run() {
                updateButtonListener();
                cardView0.setOnClickListener(CharacteristicAlarmActivity.this);
                cardView1.setOnClickListener(CharacteristicAlarmActivity.this);
                cardView3.setOnClickListener(CharacteristicAlarmActivity.this);
            }

        });
        sceneD.setEnterAction(new Runnable() {
            @Override
            public void run() {
                updateButtonListener();
                cardView0.setOnClickListener(CharacteristicAlarmActivity.this);
                cardView1.setOnClickListener(CharacteristicAlarmActivity.this);
                cardView2.setOnClickListener(CharacteristicAlarmActivity.this);

            }

        });
    }

    private void enabledForCheckBox(View view) {
        switch (view.getId()) {
            case R.id.cbNoRepeat:
                if (cbNoRepeat.isChecked()) {
                    cbRepeatDayOfWeek.setChecked(false);
                    cbRepeatEveryDay.setChecked(false);
                    cbRepeatShift.setChecked(false);
                    cbManualSetting.setChecked(false);
                    cbRepeatDayOfMonth.setChecked(false);
                }
                break;
            case R.id.cbRepeatEveryDay:
                if (cbRepeatEveryDay.isChecked()) {
                    cbRepeatDayOfMonth.setChecked(false);
                    cbRepeatDayOfWeek.setChecked(false);
                    cbNoRepeat.setChecked(false);
                    cbRepeatShift.setChecked(false);
                    cbManualSetting.setChecked(false);
                }

                break;
            case R.id.cbRepeatDayOfMonth:
                if (cbRepeatDayOfMonth.isChecked()) {
                    cbRepeatDayOfWeek.setChecked(false);
                    cbRepeatEveryDay.setChecked(false);
                    cbNoRepeat.setChecked(false);
                    cbRepeatShift.setChecked(false);
                    cbManualSetting.setChecked(false);
                }
                break;
            case R.id.cbRepeatDayOfWeek:
                if (cbRepeatDayOfWeek.isChecked()) {
                    cbRepeatDayOfMonth.setChecked(false);
                    cbRepeatEveryDay.setChecked(false);
                    cbNoRepeat.setChecked(false);
                    cbRepeatShift.setChecked(false);
                    cbManualSetting.setChecked(false);
                }
                break;
            case R.id.cbRepeatShift:
                if (cbRepeatShift.isChecked()) {
                    cbRepeatDayOfMonth.setChecked(false);
                    cbRepeatEveryDay.setChecked(false);
                    cbNoRepeat.setChecked(false);
                    cbRepeatDayOfWeek.setChecked(false);
                    cbManualSetting.setChecked(false);
                    break;
                }
            case R.id.cbManualSetting:
                if (cbManualSetting.isChecked()) {
                    cbRepeatDayOfMonth.setChecked(false);
                    cbRepeatEveryDay.setChecked(false);
                    cbNoRepeat.setChecked(false);
                    cbRepeatDayOfWeek.setChecked(false);
                    cbRepeatShift.setChecked(false);
                }
                break;
        }
    }
    private void saveAlarm(){

        Calendar now = Calendar.getInstance();
        Integer hoursNow = now.get(Calendar.HOUR_OF_DAY);
        Integer minutesNow = now.get(Calendar.MINUTE);
        if(hours>=hoursNow){
            if (minutes>minutesNow){
                Calendar sds = Calendar.getInstance();
                sds.set(Calendar.HOUR_OF_DAY, hours);
                sds.set(Calendar.MINUTE, minutes);
                differenceTimeMills= sds.getTimeInMillis();
                Context context= this.getApplicationContext();
                BroadcastReceiverForAlarm broadcastReceiverForAlarm = new BroadcastReceiverForAlarm();
                broadcastReceiverForAlarm.setAlarm(context,differenceTimeMills);

            }

        }

    }

}
