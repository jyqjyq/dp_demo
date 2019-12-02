package com.dapeng.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dapeng.demo.mapper")
public class InterfaceManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterfaceManageApplication.class, args);
    }

}
