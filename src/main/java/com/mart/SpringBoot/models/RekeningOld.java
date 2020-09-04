package com.mart.SpringBoot.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RekeningOld {

    private UUID id;
    private int saldo;
    private boolean locked;

    public RekeningOld(){
        this.id = UUID.randomUUID();
        this.saldo = 0;
    }

    public RekeningOld(String s){
        this.id = UUID.fromString(s);
        this.saldo = 0;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public UUID getId() {
        return id;
    }

}
