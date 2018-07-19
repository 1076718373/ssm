package com.ssm.service.rabbit;


import com.ssm.controller.SocketController;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author fly
 * 消息消费者
 */
@Service("messageCustomer")
public class Customer implements MessageListener {

    @Override
    public void onMessage(Message message) {
        for (SocketController socketController : SocketController.controllers) {
            try {
                socketController.onSendMessage(new String(message.getBody(), "UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }

        }

    }
}