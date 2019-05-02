package com.example.integracao;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.integracao.Models.Task;
import com.example.integracao.Models.WeekDay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends ListActivity {

    private ArrayList<WeekDay> weekDays = new ArrayList<>();
    private ArrayList<Task> avaiableTasks = new ArrayList<>();
    private Button btnJourney;
    private Button btnTask;
    private Button btnReport;

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

        this.btnTask = (Button) findViewById(R.id.btn_task);
        this.btnTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), TaskRegisterActivity.class);
                Bundle params = new Bundle();
                params.putSerializable("tasks", MainActivity.this.avaiableTasks);
                i.putExtras(params);
                startActivityForResult(i, 1);
            }
        });

        this.btnReport= (Button) findViewById(R.id.btn_report);
        this.btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ReportActivity.class);
                Bundle params = new Bundle();
                params.putSerializable("weekDays", MainActivity.this.weekDays);
                i.putExtras(params);
                startActivity(i);
            }
        });


        this.updateAdapter();

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        if (this.avaiableTasks.size() > 0) {
            Intent i = new Intent(v.getContext(), DayRegisterActivity.class);
            Bundle params = new Bundle();
            params.putSerializable("tasks", this.avaiableTasks);
            params.putSerializable("day", this.weekDays.get(position));
            params.putInt("dayPosition", position);
            i.putExtras(params);
            startActivityForResult(i, 2);
        } else {
            Toast.makeText(this, "Por favor, cadastre uma atividade primeiro!", Toast.LENGTH_LONG).show();
        }
        super.onListItemClick(l, v, position, id);
    }

    private void updateAdapter() {
        String[] from = {"week_day", "balance", "total_balance", "journey"};
        List<HashMap<String, String>> aList = new ArrayList<>();

        for (int i = 0; i < this.weekDays.size(); i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("week_day", this.weekDays.get(i).getName());
            hm.put("balance", "Completado: " + this.weekDays.get(i).getExecutedHoursFomated());
            hm.put("total_balance", "Saldo: " + this.weekDays.get(i).getBalance());
            hm.put("journey", "Jornada: " + this.weekDays.get(i).getJourneyFomated());
            aList.add(hm);
        }
        int[] to = {R.id.week_day, R.id.balance, R.id.total_balance, R.id.week_day_journey_disp};

        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_layout, from, to) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View row = super.getView(position, convertView, parent);
                TextView balance = (TextView) row.findViewById(R.id.total_balance);
                if (weekDays.get(position).getBalanceMinutes() >= 0) {
                    balance.setTextColor(Color.BLUE);
                } else {
                    balance.setTextColor(Color.RED);
                }

                return row;
            }
        };

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
                    this.updateAdapter();
                }
            }
        } else if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bundle params = data.getExtras();
                if (params != null) {
                    this.avaiableTasks = (ArrayList<Task>) params.getSerializable("tasks");
                }
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                Bundle params = data.getExtras();
                if (params != null) {
                    int position = params.getInt("position");
                    this.weekDays.set(position, (WeekDay) params.getSerializable("day"));
                    this.updateAdapter();
                }
            }
        }
    }
}
