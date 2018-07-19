package com.ssm.controller;

import com.ssm.util.PasswordEncoder;

public class Test {
    @org.junit.jupiter.api.Test
    public void test1(){
        PasswordEncoder passwordEncoder = new PasswordEncoder("hmb", "MD5");
        String encode = passwordEncoder.encode("123456", 5);
        System.out.println(encode);
    }
}
