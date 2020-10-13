package com.mart.les.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sqs")
public class SQSController {

    private static final Logger LOG = LoggerFactory.getLogger(SQSController.class);

    private final QueueMessagingTemplate queueMessagingTemplate;

    public SQSController(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    @Value("${cloud.aws.end-point.uri}")
    private String sqsEndpoint;

    @GetMapping
    public void sendMessage(){
        queueMessagingTemplate.send(sqsEndpoint, MessageBuilder.withPayload("Hello world").build());
    }
}
