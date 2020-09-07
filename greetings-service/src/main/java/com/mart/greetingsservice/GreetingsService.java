package com.mart.greetingsservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GreetingsService {

    @HystrixCommand(fallbackMethod = "getGreetFallback")
    public String getGreet(){
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate template = builder.build();
        String s = template.getForObject("http://localhost:8080/user", String.class);

        return "Hello! " + s;
    }

    private String getGreetFallback(){
        return "Standard greet!";
    }

}
