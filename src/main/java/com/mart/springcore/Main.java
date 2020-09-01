package com.mart.springcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        try{
            ProdController mainController = (ProdController) context.getBean(MainController.class);

            String s = mainController.invertString("Hello World");
            System.out.println(s);

            System.out.println(mainController.countWords("This is four words"));
            System.out.println(mainController.countWords("This is eight words"));
        } catch(Exception e){
            System.out.println(e);
        }
    }

}
