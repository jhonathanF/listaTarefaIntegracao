package com.example.integracao.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.integracao.Models.WeekDay;
import com.example.integracao.R;

import java.util.ArrayList;

public class CustomListJourneyAdapter extends ArrayAdapter<WeekDay> {

    private ArrayList<WeekDay> weekDays;

    public CustomListJourneyAdapter(Context context, int textViewResourceId, ArrayList<WeekDay> objects) {
        super(context, textViewResourceId, objects);
        this.weekDays = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }


    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.listview_journey_layout, null);

        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar_journey);
        final TextView weekDay = (TextView) view.findViewById(R.id.week_day_journey);
        final TextView hours = (TextView) view.findViewById(R.id.week_day_hours_journey);
        seekBar.setMax(1440);

        weekDay.setText(this.weekDays.get(position).getName());
        hours.setText("Jornada: " + getMinutesFomated(position));
        seekBar.setProgress(this.weekDays.get(position).getJourney());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                updateWeekDayJourney(position, progress);
                hours.setText("Jornada: " + getMinutesFomated(position));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }
        });

        return view;
    }

    private void updateWeekDayJourney(int position, int progress) {
        this.weekDays.get(position).setJourney(progress);
        Log.i("Posição", this.weekDays.get(position).getName() + "--" + this.weekDays.get(position).getJourney());
    }

    private String getMinutesFomated(int position) {
       return this.weekDays.get(position).getJourneyFomated();
    }
}
