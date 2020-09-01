package com.mart.springcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@ComponentScan(basePackages = {"com.mart.springcore"})
public class SpringConfig {

    public SpringConfig(ConfigurableEnvironment env){
        env.setActiveProfiles("prod");
    }
}
