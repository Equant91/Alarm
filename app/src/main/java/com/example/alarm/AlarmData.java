package com.example.alarm;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlarmData {
    public static ArrayList<AlarmData> ArrayAlarmData = new ArrayList<>();
    Integer hours, minutes;
    String timeAlarm;
    String descriptionAlarm;
    boolean onStart = true;
    private int TypeRepeat = 0;

    public final int NO_REPEAT = 0;
    public final int EVERY_DAY = 1;
    public final int DAY_OF_WEEK = 2;
    public final int SHIFT_REPEAT = 3;
    public final int MANUAL_REPEAT = 4;


    private int firstValueShift;
    private int secondValueShift;


    private boolean [] dayOfWeek  = new boolean[7];

    AlarmData(Integer Hours, Integer Minutes, String TimeAlarm){
        hours = Hours;
        minutes = Minutes;
        timeAlarm = TimeAlarm;
    }

    public String getTimeAlarm(){
        return  timeAlarm;
    }
    public void setTimeAlarm(Integer Hours, Integer Minutes, String TimeAlarm){
        hours = Hours;
        minutes = Minutes;
        timeAlarm = TimeAlarm;
    }
    public void setRepeatType(int repeatType) {
        this.TypeRepeat = repeatType;
    }


    public void setOnStart(boolean onStart) {
        this.onStart = onStart;
    }

    public void setFirstValueShift(int firstValueShift) { ///////сделать обработчик, если равна нулю вывести сообщение;
        firstValueShift = firstValueShift;
    }

        public void setSecondValueShift ( int secondValueShift){
            secondValueShift = secondValueShift;

    }
}
