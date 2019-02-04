package com.example.alarm;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

public class MyPagerAdapter extends FragmentPagerAdapter {

    FragmentManager mFragmentManager;
    MainFragment mainFragment;


    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
 mFragmentManager = fm;

}public MainFragment getMainFragment(){
        return mainFragment;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:

                mainFragment = new MainFragment();
                    return mainFragment;


            case 1:
                return  new CalendarFragment();
        }
        return null;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Будильник";
            case 1:
                return "Календарь";

        }
        return null;
    }

}
