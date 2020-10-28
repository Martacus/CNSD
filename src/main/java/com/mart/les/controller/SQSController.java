package com.mart.les.controller;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.MediaType;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

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
    public void gg(@RequestParam MultipartFile file) throws IOException {
        final SendMessageRequest myMessageRequest =
                new SendMessageRequest(sqsEndpoint, Base64.getEncoder().encodeToString(file.getBytes()));
        amazonSQSClient.sendMessage(myMessageRequest);
    }

    @GetMapping("consume")
    public void consumeMessage(){
        try{
            final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(sqsEndpoint);
            List<Message> messages = amazonSQSClient.receiveMessage(receiveMessageRequest).getMessages();
            if(messages != null){
                for(Message message : messages){
                    LOG.info("Recieved message from {}", sqsEndpoint + " " + message.getBody());
                }
                LOG.info("Recieved message from {}", sqsEndpoint);
            } else {
                LOG.info("No message from {}", sqsEndpoint);
            }
        } catch (Exception e){
            LOG.info("Exception from {}", sqsEndpoint);
            LOG.info(e.getMessage());
        }
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
