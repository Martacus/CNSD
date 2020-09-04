package com.mart.SpringBoot.models;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class AccountOld {

    @NotBlank(message = "Name is mandatory")
    private String name;
    private UUID id;

    public AccountOld(){
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
