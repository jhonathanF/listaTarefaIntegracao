package com.example.integracao;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.integracao.Models.Task;

import java.util.ArrayList;

public class TaskRegisterActivity extends ListActivity {


    private ArrayList<Task> tasks;
    private Button btnInclude;
    private EditText newTaskName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_register);

        this.btnInclude = (Button) findViewById(R.id.btn_include_Activity);
        this.newTaskName = (EditText) findViewById(R.id.new_activity_name);

        Intent intent = getIntent();

        if (intent != null) {
            Bundle params = intent.getExtras();
            if (params != null) {
                this.tasks = (ArrayList<Task>) params.getSerializable("tasks");
            }
        }
        String[] from = {"task"};

        this.btnInclude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateTaskName()) {
                    addNewTask();
                    updateAdapter();
                    newTaskName.setText("");
                }
            }
        });

        updateAdapter();
    }

    @Override
    public void finish() {
        Intent devolve = new Intent();
        devolve.putExtra("tasks", this.tasks);
        setResult(Activity.RESULT_OK, devolve);
        super.finish();
    }

    private void updateAdapter() {
        ArrayList<String> names = new ArrayList<>();
        for (Task task : this.tasks) {
            names.add(task.getName());
        }

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        setListAdapter(myAdapter);
    }

    private boolean validateTaskName() {
        if (this.newTaskName.getText().toString() == null || this.newTaskName.getText().toString().length() <= 0)
            return false;
        for (Task task : this.tasks) {
            if (task.getName().trim().equals(this.newTaskName.getText().toString())) {
                Toast.makeText(this, "Atividade já existente!",
                        Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
    }

    private void addNewTask() {
        this.tasks.add(new Task(this.newTaskName.getText().toString()));
    }
}
