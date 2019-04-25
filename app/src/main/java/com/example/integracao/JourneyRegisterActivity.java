package com.example.integracao;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;

import com.example.integracao.Adapters.CustomListAdapter;
import com.example.integracao.Models.WeekDay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JourneyRegisterActivity extends ListActivity {

    private ArrayList<WeekDay> weekDays;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.journey_register);

        Intent intent = getIntent();

        if (intent != null) {
            Bundle params = intent.getExtras();
            if (params != null) {
                this.weekDays = (ArrayList<WeekDay>) params.getSerializable("weekDays");
            }
        }
        String[] from = {"week_day"};

        CustomListAdapter myAdapter = new CustomListAdapter(getBaseContext(), R.layout.listview_journey_layout, this.weekDays);
        setListAdapter(myAdapter);

        this.btnSave = (Button) findViewById(R.id.btn_save_journey);
        this.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devolve = new Intent();
                devolve.putExtra("weekDays", JourneyRegisterActivity.this.weekDays);
                setResult(Activity.RESULT_OK, devolve);
                finish();
            }
        });
    }

}
