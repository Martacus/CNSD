package com.mart.SpringBoot.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String name;

    @ManyToMany(cascade=CascadeType.ALL)
    private Set<Account> accounts = new HashSet<>();

    public void addAccount(Account account){
        this.accounts.add(account);
    }
}
