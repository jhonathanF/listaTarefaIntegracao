package com.example.integracao.Models;

import android.icu.util.DateInterval;

public class ExecutedActivity {
    private Activity activity;
    private DateInterval dateInterval;
    private Integer importance;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public DateInterval getDateInterval() {
        return dateInterval;
    }

    public void setDateInterval(DateInterval dateInterval) {
        this.dateInterval = dateInterval;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }
}
