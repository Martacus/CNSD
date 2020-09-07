package com.mart.userservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    public int id;
    public String username;
    public String email;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email=" + email +
                '}';
    }
}
