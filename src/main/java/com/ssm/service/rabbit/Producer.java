package com.ssm.service.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

/**
 * @author fly
 * 消息生产者
 */
@Service
@EnableAsync
public class Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * @param exchange 交换机地址
     * @param queue    队列地址
     * @param object   发送对象
     */
    public void setMessageForQueue(String exchange, String queue, Object object) {
        //发送消息
        amqpTemplate.convertAndSend(exchange, queue, object);
    }

    @Async
    public void test1() throws InterruptedException {
        System.out.println("a1");
        this.setMessageForQueue("mq_exchange", "mq_url", true);
        Thread.sleep(3000);
        System.out.println("a2");
    }

    @Async
    public void test2() throws InterruptedException {
        System.out.println("b1");
        this.setMessageForQueue("mq_exchange", "mq_url", true);
        Thread.sleep(3000);
        System.out.println("b2");
    }

    @Async
    public void test3() throws InterruptedException {
        System.out.println("c1");
        this.setMessageForQueue("mq_exchange", "mq_url", true);
        Thread.sleep(3000);
        System.out.println("c2");
    }


}