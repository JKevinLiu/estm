package com.yucheng.estm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yucheng.estm.mapper")
public class WebApplication {
    public static void main(String[] args) {
        //application能扫描它同级和下级的包
        SpringApplication.run(WebApplication.class, args);
    }

}