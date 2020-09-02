package com.mart.SpringBoot.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Account {

    private String name;
    private UUID id;

    public Account(){
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
