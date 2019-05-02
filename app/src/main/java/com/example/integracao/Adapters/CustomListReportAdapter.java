package com.example.integracao.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.integracao.Models.WeekDay;
import com.example.integracao.R;

import java.util.ArrayList;

public class CustomListReportAdapter extends ArrayAdapter<WeekDay> {

    private ArrayList<WeekDay> weekDays;

    public CustomListReportAdapter(Context context, int textViewResourceId, ArrayList<WeekDay> objects) {
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
        view = inflater.inflate(R.layout.listview_register_layout, null);

        final TextView weekDayName = view.findViewById(R.id.register_week_day_name);
        final TextView weekDayBalance = view.findViewById(R.id.register_week_day_balance);
        final TextView weekDayImportance = view.findViewById(R.id.register_week_day_importance);
        final EditText executedTasks = view.findViewById(R.id.ta_tasks);

        if (weekDays.get(position).getBalanceMinutes() >= 0) {
            weekDayBalance.setTextColor(Color.BLUE);
        } else {
            weekDayBalance.setTextColor(Color.RED);
        }
        weekDayName.setText(this.weekDays.get(position).getName());
        weekDayBalance.setText("Saldo:" + this.weekDays.get(position).getBalance());
        weekDayImportance.setText("Importância: " + this.weekDays.get(position).getImportance());

        executedTasks.setText(this.weekDays.get(position).getExecutedTasksReport());
        return view;
    }
}
