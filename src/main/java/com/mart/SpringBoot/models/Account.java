package com.mart.SpringBoot.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "account")
public class Account {
    @Id
    private UUID id;
    private int saldo;
    private boolean locked;

    @ManyToMany
    private Set<User> users = new HashSet<>();



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

}
