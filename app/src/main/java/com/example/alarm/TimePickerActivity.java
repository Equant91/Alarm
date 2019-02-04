package com.example.alarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerActivity extends AppCompatActivity {
TimePicker mTimePicker;
Button bSetTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_picker_activity);
        mTimePicker = (TimePicker) findViewById(R.id.time_picker);

        Calendar now = Calendar.getInstance();

        mTimePicker.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
        mTimePicker.setCurrentMinute(now.get(Calendar.MINUTE));
        bSetTime = findViewById(R.id.bSetTime);
        bSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Integer hours = mTimePicker.getCurrentHour();
                Integer minutes = mTimePicker.getCurrentMinute();
                String min = minutes < 10 ? "0" + minutes : "" + minutes;
                Intent intent = new Intent();
                intent.putExtra("hours" , hours);
                intent.putExtra("minutes" , minutes);
                intent.putExtra("data", "" + hours + " : " + min);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
