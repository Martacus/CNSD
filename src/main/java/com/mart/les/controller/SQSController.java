package com.mart.les.controller;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.sqs.AmazonSQS;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.MediaType;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/sqs")
public class SQSController {

    private static final Logger LOG = LoggerFactory.getLogger(SQSController.class);

    private final AmazonSQS amazonSQSClient;
    private final QueueMessagingTemplate queueMessagingTemplate;

    public SQSController(QueueMessagingTemplate queueMessagingTemplate, AmazonSQS amazonSQSClient) {
        this.queueMessagingTemplate = queueMessagingTemplate;
        this.amazonSQSClient = amazonSQSClient;
    }

    @Value("${cloud.aws.end-point.uri}")
    private String sqsEndpoint;

    @GetMapping
    public void sendMessage(){
        queueMessagingTemplate.send(sqsEndpoint, MessageBuilder.withPayload("Hello world").build());
        LOG.info("Send message to {}", sqsEndpoint);
    }

    @PostMapping(value = "/test", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public String gg(@RequestParam MultipartFile file){
        return amazonSQSClient.sendMessage();
        //Message message = queueMessagingTemplate.receive(sqsEndpoint);
        //log.info("Recieved Message {}", message.getPayload().toString());
    }

//    private File convertMultiPartToFile(MultipartFile file) throws IOException {
//        File convFile = new File(file.getOriginalFilename());
//        FileOutputStream fos = new FileOutputStream(convFile);
//        fos.write(file.getBytes());
//        fos.close();
//        return convFile;
//    }
//    private String generateFileName(MultipartFile multiPart) {
//        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
//    }
//
//    private void uploadFileTos3bucket(String fileName, File file) {
//        amazonSQS.putObject(new PutObjectRequest("martles212", fileName, file)
//                .withCannedAcl(CannedAccessControlList.PublicRead));
//    }
//
//    public String uploadFile(MultipartFile multipartFile) {
//
//        String fileUrl = "";
//        try {
//            File file = convertMultiPartToFile(multipartFile);
//            String fileName = generateFileName(multipartFile);
//            fileUrl = "https://s3.eu-west-1.amazonaws.com" + "/" + "martles212" + "/" + fileName;
//            uploadFileTos3bucket(fileName, file);
//            file.delete();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return fileUrl;
//    }
//
//    public String deleteFileFromS3Bucket(String fileUrl) {
//        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
//        amazonSQS.deleteObject(new DeleteObjectRequest( "martles212/", fileName));
//        return "Successfully deleted";
//    }
}
