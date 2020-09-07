package com.mart.userservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class UserService {



    @HystrixCommand(fallbackMethod = "defaultName")
    public String getName(){
        int i = new Random().nextInt(10) + 1;
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate template = builder.build();
        User user = template.getForObject("https://jsonplaceholder.typicode.com/users/" + i, User.class);

        return user.username;
    }

    private String defaultName(){
        return "Marry";
    }
}
