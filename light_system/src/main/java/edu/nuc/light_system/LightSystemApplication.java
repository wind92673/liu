package edu.nuc.light_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("edu.nuc.light_system.mappers")
@EnableScheduling
public class LightSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LightSystemApplication.class, args);
    }

}
