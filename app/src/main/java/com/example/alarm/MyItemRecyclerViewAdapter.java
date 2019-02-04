package com.example.alarm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.alarm.MainFragment.OnListFragmentInteractionListener;
import java.util.ArrayList;



public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    ArrayList<AlarmData> alarmData;
    private final OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(ArrayList<AlarmData> items, OnListFragmentInteractionListener listener) {
        alarmData = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.basic_alarm_for_main_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        View view = holder.mView;
        holder.mIdView.setText("" + (position + 1));
        TextView tvTimeSet = holder.mView.findViewById(R.id.tvTimeSet);
        tvTimeSet.setText(alarmData.get(position).getTimeAlarm());
        ImageButton deleteAlarm = view.findViewById(R.id.ibDeleteAlarm);
        deleteAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmData.ArrayAlarmData.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (null != mListener) {
                    mListener.onListFragmentInteraction(position);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return AlarmData.ArrayAlarmData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public AlarmData mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.tvNumberAlarm);

        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public interface FirstPageFragmentListener {
        void onSwitchToNextFragment();
    }
}
