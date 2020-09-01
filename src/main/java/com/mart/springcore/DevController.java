package com.mart.springcore;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("dev")
public class DevController implements MainController{

    public List<String> strings = new ArrayList<>();
    public int wordCount = 0;

    @Override
    public String invertString(String s){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s);
        stringBuilder.reverse();
        return stringBuilder.toString();
    }

    @Override
    public int countWords(String s){
        strings.add(s);
        String[] strings = s.split(" ");
        wordCount += strings.length;
        return wordCount;
    }
}
