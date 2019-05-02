package com.example.integracao.Models;

import java.io.Serializable;

public class Task implements Serializable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
