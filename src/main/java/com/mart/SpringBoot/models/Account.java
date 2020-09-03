package com.mart.SpringBoot.models;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Account {

    @NotBlank(message = "Name is mandatory")
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
