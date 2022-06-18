package com.gestion_parc.demo.beans;

public class Statistique {

    private Double cost=0.0;
    private String month;

    public Double getCost() {
        return cost;
    }

    public String getMonth() {
        return month;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
