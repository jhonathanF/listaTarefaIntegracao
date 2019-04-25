package com.example.integracao;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.integracao.Models.WeekDay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends ListActivity  {

    private ArrayList<WeekDay> weekDays = new ArrayList<>();
    private Button btnJourney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.weekDays.add(new WeekDay("Segunda"));
        this.weekDays.add(new WeekDay("Terça"));
        this.weekDays.add(new WeekDay("Quarta"));
        this.weekDays.add(new WeekDay("Quinta"));
        this.weekDays.add(new WeekDay("Sexta"));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnJourney = (Button) findViewById(R.id.btn_journey);
        this.btnJourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), JourneyRegisterActivity.class);
                Bundle params = new Bundle();
                params.putSerializable("weekDays", MainActivity.this.weekDays);
                i.putExtras(params);
                startActivityForResult(i, 0);
            }
        });

        // Keys used in Hashmap
        String[] from = {"week_day", "balance"};

        List<HashMap<String, String>> aList = new ArrayList<>();

        for (int i = 0; i < this.weekDays.size(); i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("week_day", this.weekDays.get(i).getName());
            hm.put("balance", String.valueOf(i));
            aList.add(hm);
        }
        int[] to = {R.id.week_day, R.id.balance};

        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_layout, from, to);


        ArrayAdapter<WeekDay> array = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.weekDays);
        setListAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 0) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Bundle params = data.getExtras();
                if (params != null) {
                    this.weekDays = (ArrayList<WeekDay>) params.getSerializable("weekDays");
                }
            }
        }
    }
}
