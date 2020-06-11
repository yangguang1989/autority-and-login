package com.itheima.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBCryptPasswordEncoder {

    public static void main(String[] args) {
        BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
        String mm_pub = "3333";
        String mm_encode = bcp.encode(mm_pub);
        System.out.println(mm_encode);
        //bcp.matches(mm_pub,mm_encode)，第一个参数是前端传递过来的明文密码，如123456，第二个参数是添加用户时存储的密码
        if(bcp.matches(mm_pub,mm_encode)){
            System.out.println("密码校验成功");
        }else{
            System.out.println("密码错误");
        }
    }
}
