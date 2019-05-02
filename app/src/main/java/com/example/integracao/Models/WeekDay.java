package com.example.integracao.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeekDay implements Serializable {
    private String name;
    private ArrayList<ExecutedTask> executedTasks = new ArrayList<>();
    private int journey = 0;

    public WeekDay(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExecutedTask> getExecutedTasks() {
        return executedTasks;
    }

    public void setExecutedTasks(ArrayList<ExecutedTask> executedTasks) {
        this.executedTasks = executedTasks;
    }

    public int getJourney() {
        return journey;
    }

    public void setJourney(int journey) {
        this.journey = journey;
    }

    public String getJourneyFomated() {
        int hours = this.journey / 60;
        int min = this.journey % 60;
        String sHours;
        String sMin;
        if (hours < 10)
            sHours = "0" + hours;
        else
            sHours = String.valueOf(hours);
        if (min < 10)
            sMin = "0" + min;
        else
            sMin = String.valueOf(min);

        return sHours + "h:" + sMin + "min";
    }

    private int getExecutedMinutes() {
        int executedMinutes = 0;
        for (ExecutedTask executed : this.executedTasks) {
            executedMinutes += executed.getMinutesInActivity();
        }

        return executedMinutes;
    }

    public String getExecutedHoursFomated() {
        int executedMinutes = getExecutedMinutes();
        int hours = executedMinutes / 60;
        int min = executedMinutes % 60;
        String sHours;
        String sMin;
        if (hours < 10)
            sHours = "0" + hours;
        else
            sHours = String.valueOf(hours);
        if (min < 10)
            sMin = "0" + min;
        else
            sMin = String.valueOf(min);

        return sHours + "h:" + sMin + "min";
    }

    public int getBalanceMinutes() {
        return getExecutedMinutes() - journey;
    }

    public String getBalance() {
        int balanceMinutes = getBalanceMinutes();
        boolean isNegative = false;
        if (balanceMinutes < 0) {
            balanceMinutes = balanceMinutes * -1;
            isNegative = true;
        }
        int hours = balanceMinutes / 60;
        int min = balanceMinutes % 60;
        String sHours;
        String sMin;
        if (hours < 10)
            sHours = "0" + hours;
        else
            sHours = String.valueOf(hours);
        if (min < 10)
            sMin = "0" + min;
        else
            sMin = String.valueOf(min);


        if (!isNegative) {
            return sHours + "h:" + sMin + "min";
        } else {
            return "- " + sHours + "h:" + sMin + "min";
        }
    }

    public String getImportance() {
        String sImportance = "";
        int importance = 0;

        for (ExecutedTask task : executedTasks) {
            importance += task.getImportance();
        }

        if (executedTasks.size() > 0)
            importance = importance / executedTasks.size();

        if (importance < 90) {
            sImportance = "Baixa";
        } else if (importance >= 90 && importance <= 156) {
            sImportance = "Média";
        } else {
            sImportance = "Alta";
        }
        return sImportance + "(" + importance + ")";
    }

    public String getExecutedTasksReport() {
        String returnString = "";

        for (ExecutedTask executedTask : executedTasks) {
            returnString += executedTask.getTask().getName() + " - " + executedTask.getMinutesInActivityFormated() + "\n";
        }

        return returnString;
    }
}
