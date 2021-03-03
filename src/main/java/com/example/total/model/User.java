package com.example.total.model;

public class User {
    private int id;
    private String name;
    private int amount;

    public User(int id, String name, int amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public User(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
