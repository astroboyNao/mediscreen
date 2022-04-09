package com.abernathy.assess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class AssessApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssessApplication.class, args);
    }
}
