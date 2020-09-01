package com.mart.springcore;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public interface MainController {
    abstract String invertString(String s);
    abstract int countWords(String s);
}
