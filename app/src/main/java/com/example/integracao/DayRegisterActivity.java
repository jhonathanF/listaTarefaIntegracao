package com.example.integracao;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.integracao.Models.ExecutedTask;
import com.example.integracao.Models.Task;
import com.example.integracao.Models.WeekDay;

import java.util.ArrayList;

public class DayRegisterActivity extends AppCompatActivity {
    private ArrayList<String> array_spinner;
    private ArrayList<Task> avaiableTasks;
    private WeekDay weekDay;
    private ExecutedTask newExecutedTask = new ExecutedTask();
    private int position;
    private EditText timeFrom;
    private EditText timeTo;
    private Spinner spinner;
    private SeekBar seekbarImportance;
    private Button btnColor;
    private Button btnSave;
    private TextView tvImportance;
    private boolean firstSet = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_register_activity);
        spinner = (Spinner) findViewById(R.id.spinner_tasks);
        timeFrom = (EditText) findViewById(R.id.et_time_from);
        timeTo = (EditText) findViewById(R.id.et_time_to);
        seekbarImportance = findViewById(R.id.seekBar_importance);
        btnColor = findViewById(R.id.btnColor);
        btnSave = findViewById(R.id.btn_save_day);
        tvImportance = findViewById(R.id.tv_importance);
        tvImportance.setText("Importância: Baixa (0)");
        btnColor.setBackgroundColor(Color.rgb(0, 255, 0));
        seekbarImportance.setMax(255);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle params = intent.getExtras();
            if (params != null) {
                this.avaiableTasks = (ArrayList<Task>) params.getSerializable("tasks");
                this.weekDay = (WeekDay) params.getSerializable("day");
                this.position = params.getInt("dayPosition");
            }
        }

        array_spinner = new ArrayList<>();

        for (Task task : this.avaiableTasks) {
            array_spinner.add(task.getName());
        }


        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        timeFrom.setText("00:00");
        timeTo.setText("01:00");
        this.setSpinnerListener();
        this.setTimesListener();
        this.setSeekBarListener();
        this.setBtnSaveListener();

    }


    private void setSpinnerListener() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newExecutedTask.setTask(avaiableTasks.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setTimesListener() {
        this.timeFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(DayRegisterActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if (selectedHour < newExecutedTask.getFinalHour() || (selectedHour == newExecutedTask.getFinalHour() && selectedMinute < newExecutedTask.getFinalMinute())) {
                            newExecutedTask.setInitHour(selectedHour);
                            newExecutedTask.setInitMinute(selectedMinute);
                            timeFrom.setText(newExecutedTask.getInitTimeFormated());
                        } else if (firstSet) {
                            newExecutedTask.setInitHour(selectedHour);
                            newExecutedTask.setInitMinute(selectedMinute);
                            timeFrom.setText(newExecutedTask.getInitTimeFormated());
                            if (selectedHour < 24) {
                                newExecutedTask.setFinalHour(selectedHour + 1);
                                newExecutedTask.setFinalMinute(selectedMinute);
                            } else {
                                newExecutedTask.setFinalMinute(selectedMinute + 1);
                            }

                            timeTo.setText(newExecutedTask.getFinalTimeFormated());
                            firstSet = false;
                        } else {
                            Toast.makeText(DayRegisterActivity.this, "Horario inicial não pode ser maior que o final", Toast.LENGTH_LONG);
                        }
                    }
                }, 0, 0, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        this.timeTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(DayRegisterActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if (selectedHour > newExecutedTask.getInitHour() || (selectedHour == newExecutedTask.getInitHour() && selectedMinute > newExecutedTask.getInitHour())) {
                            newExecutedTask.setFinalHour(selectedHour);
                            newExecutedTask.setFinalMinute(selectedMinute);
                            timeTo.setText(newExecutedTask.getFinalTimeFormated());
                        } else if (firstSet) {
                            newExecutedTask.setFinalHour(selectedHour);
                            newExecutedTask.setFinalMinute(selectedMinute);
                            timeTo.setText(newExecutedTask.getFinalTimeFormated());
                            if (selectedHour > 0) {
                                newExecutedTask.setInitHour(selectedHour - 1);
                                newExecutedTask.setInitMinute(selectedMinute);
                            } else {
                                newExecutedTask.setInitMinute(selectedMinute - 1);
                            }

                            timeFrom.setText(newExecutedTask.getInitTimeFormated());
                            firstSet = false;
                        } else {
                            Toast.makeText(DayRegisterActivity.this, "Horario final não pode ser menor que o final", Toast.LENGTH_LONG);
                        }
                    }
                }, 1, 0, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

    private void setSeekBarListener() {
        seekbarImportance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                newExecutedTask.setImportance(progress);
                btnColor.setBackgroundColor(Color.rgb(progress, (255 - progress), 0));
                if (progress < 90) {
                    tvImportance.setText("Importância: Baixa (" + progress + ")");
                } else if (progress >= 90 && progress <= 156) {
                    tvImportance.setText("Importância: Média (" + progress + ")");
                } else {
                    tvImportance.setText("Importância: Alta (" + progress + ")");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setBtnSaveListener() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newExecutedTask.validatePeriod()) {
                    weekDay.getExecutedTasks().add(newExecutedTask);
                    Intent devolve = new Intent();
                    devolve.putExtra("day", weekDay);
                    devolve.putExtra("position", position);
                    setResult(Activity.RESULT_OK, devolve);
                    finish();
                } else {
                    Toast.makeText(DayRegisterActivity.this, "Horarios inválidos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
