package com.tinxing.baiduapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tinxing.baiduapi.dao")
public class BaiduapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaiduapiApplication.class, args);
    }

}
