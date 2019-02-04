package com.example.alarm;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.Toast;


import com.example.alarm.dummy.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainFragment.OnListFragmentInteractionListener, CalendarFragment.OnFragmentInteractionListener {


    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    CalendarFragment calendarFragment;
    MyPagerAdapter pagerAdapter;
    FloatingActionButton fbAddAlarm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (AlarmData.ArrayAlarmData.size() < 1) {
            AlarmData.ArrayAlarmData.add(new AlarmData(11, 23, "11 : 23"));

        }


        fragmentManager = getSupportFragmentManager();
        fbAddAlarm = findViewById(R.id.fbAddAlarm);
        fbAddAlarm.setOnClickListener(this);

        pagerAdapter = new MyPagerAdapter(fragmentManager);
        ViewPager pager = findViewById(R.id.viewPager);
        pager.setAdapter(pagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fbAddAlarm:
                Toast.makeText(this, "as", Toast.LENGTH_SHORT).show();
                AlarmData.ArrayAlarmData.add(new AlarmData(12, 00, "12 : 00"));

                pagerAdapter.getMainFragment().myItemRecyclerViewAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onListFragmentInteraction(int position) {

        Intent intent = new Intent(this, CharacteristicAlarmActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
