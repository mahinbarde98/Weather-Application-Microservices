//package com.cts.service;
//
//
//import com.cts.model.UserCredentials;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaProducerService {
//
//
//
//    @Autowired
//    private KafkaTemplate<String, UserCredentials> kafkaTemplate;
//
//
//
//    public void sendMessage(UserCredentials userCredentials)
//    {
//
//        kafkaTemplate.send("case-topic",userCredentials);
//    }
//}
