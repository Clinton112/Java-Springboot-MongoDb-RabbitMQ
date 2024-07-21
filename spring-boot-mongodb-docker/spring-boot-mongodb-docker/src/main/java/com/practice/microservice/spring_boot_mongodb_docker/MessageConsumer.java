package com.practice.microservice.spring_boot_mongodb_docker;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MessageConsumer implements MessageListener {

    @Override
    public void onMessage(Message message) {
        String msgContent = Arrays.toString(message.getBody());
        // Process the message content
        System.out.println("Received message: " + msgContent);
    }
}
