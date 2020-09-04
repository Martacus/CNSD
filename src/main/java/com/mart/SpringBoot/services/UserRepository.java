package com.mart.SpringBoot.services;

import com.mart.SpringBoot.models.Account;
import com.mart.SpringBoot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

