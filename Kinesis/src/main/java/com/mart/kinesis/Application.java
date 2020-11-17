package com.mart.kinesis;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.KinesisClientLibConfiguration;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.Worker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {
    @Value( "${aws.access.key}" )
    private String accessKey;


    @Value( "${aws.secret.key}" )
    private String secretKey;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @PostConstruct
    public void run(){
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        KinesisClientLibConfiguration consumerConfig = new KinesisClientLibConfiguration(
                "les-stream",
                "les-stream",
                new AWSStaticCredentialsProvider(awsCredentials),
                "1")
                .withRegionName(Regions.EU_CENTRAL_1.getName());

        final Worker worker = new Worker.Builder()
                .recordProcessorFactory(new Factory())
                .config(consumerConfig)
                .build();

        Thread thread = new Thread(worker::run);
        thread.start();
    }

}
