package com.example.integracao.Models;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class WeekDay implements Serializable {
    private String name;
    private ArrayList<ExecutedActivity> executedActivities;
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

    public List<ExecutedActivity> getExecutedActivities() {
        return executedActivities;
    }

    public void setExecutedActivities(ArrayList<ExecutedActivity> executedActivities) {
        this.executedActivities = executedActivities;
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
}
