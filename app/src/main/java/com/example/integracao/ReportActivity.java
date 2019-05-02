package com.example.integracao;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import com.example.integracao.Adapters.CustomListJourneyAdapter;
import com.example.integracao.Adapters.CustomListReportAdapter;
import com.example.integracao.Models.WeekDay;

import java.util.ArrayList;

public class ReportActivity extends ListActivity {

    private ArrayList<WeekDay> weekDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_layout);

        Intent intent = getIntent();

        if (intent != null) {
            Bundle params = intent.getExtras();
            if (params != null) {
                this.weekDays = (ArrayList<WeekDay>) params.getSerializable("weekDays");
            }
        }

        CustomListReportAdapter myAdapter = new CustomListReportAdapter(getBaseContext(), R.layout.listview_register_layout, this.weekDays);
        setListAdapter(myAdapter);
    }
}
