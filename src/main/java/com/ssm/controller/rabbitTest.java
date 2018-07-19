package com.ssm.controller;

import com.ssm.service.rabbit.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rabbit")
@CrossOrigin
@RestController
public class rabbitTest {
    @Autowired
    private Producer producer;

    @GetMapping("/test")
    public String test() {
        producer.setMessageForQueue("mq_exchange", "mq_url", "中国");
        return "succeed";
    }


    @RequestMapping("/login")
    public String login() throws InterruptedException {
        producer.test1();
        producer.test2();
        producer.test3();
        return "succeed";
    }
}
