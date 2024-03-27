//package com.cts.service;
//
//
//import com.cts.repository.UserRepository;
//import com.cts.model.UserCredentials;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaListenerSrvice {
//    @Autowired
//    public UserRepository userRepository;
//    @KafkaListener(topics = "case-topic",groupId = "topic26",containerFactory = "userListener")
//    public void listen(UserCredentials userCredentials){
//        System.out.println("Received the obsject :"+userCredentials.toString());
//        userRepository.save(userCredentials);
//
//    }
//}
