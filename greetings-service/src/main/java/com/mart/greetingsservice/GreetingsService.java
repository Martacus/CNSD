package com.mart.greetingsservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients
@Service
public class GreetingsService {

    private final UserClient client;

    public GreetingsService(UserClient client) {
        this.client = client;
    }

    @HystrixCommand(fallbackMethod = "getGreetFallback")
    public String getGreet(){
        String s = "";
       try{
           s = client.getUser();
       } catch (Exception e){
           System.out.println(e);
       }

        return "Hello! " + s;
    }

    private String getGreetFallback(){
        return "Standard greet!";
    }

}
