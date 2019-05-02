package com.example.integracao.Models;

import java.io.Serializable;

public class ExecutedTask implements Serializable {
    private Task task;
    private int initHour;
    private int initMinute;
    private int finalHour = 1;
    private int finalMinute;
    private int importance;


    public boolean validatePeriod() {
        if (initHour == finalHour && initMinute == finalMinute) {
            return false;
        }
        return true;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public int getMinutesInActivity() {
        int hourDiff = finalHour - initHour;
        int minutesDiff = finalMinute - initMinute;

        int total = (hourDiff * 60) + minutesDiff;
        return total;
    }

    public String getMinutesInActivityFormated(){
        int executedMinutes = getMinutesInActivity();
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


    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getInitHour() {
        return initHour;
    }

    public void setInitHour(int initHour) {
        this.initHour = initHour;
    }

    public int getInitMinute() {
        return initMinute;
    }

    public void setInitMinute(int initMinute) {
        this.initMinute = initMinute;
    }

    public int getFinalHour() {
        return finalHour;
    }

    public void setFinalHour(int finalHour) {
        this.finalHour = finalHour;
    }

    public int getFinalMinute() {
        return finalMinute;
    }

    public void setFinalMinute(int finalMinute) {
        this.finalMinute = finalMinute;
    }

    public String getInitTimeFormated() {
        String sHours;
        String sMin;
        if (initHour < 10)
            sHours = "0" + initHour;
        else
            sHours = String.valueOf(initHour);
        if (initMinute < 10)
            sMin = "0" + initMinute;
        else
            sMin = String.valueOf(initMinute);

        return sHours + ":" + sMin;
    }

    public String getFinalTimeFormated() {
        String sHours;
        String sMin;
        if (finalHour < 10)
            sHours = "0" + finalHour;
        else
            sHours = String.valueOf(finalHour);
        if (finalMinute < 10)
            sMin = "0" + finalMinute;
        else
            sMin = String.valueOf(finalMinute);

        return sHours + ":" + sMin;
    }
}
