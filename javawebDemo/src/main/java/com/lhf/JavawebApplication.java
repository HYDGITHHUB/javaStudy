package com.lhf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lhf.mapper")
public class JavawebApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavawebApplication.class, args);
    }

}
