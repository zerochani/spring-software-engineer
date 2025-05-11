package com.amigoscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController //restful 웹 요청을 처리하는 메서드
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @GetMapping //루트 경로 /로 들어온 get요청을 처리
    public String helloWorld(){
        return "Hello World Spring Boot!";
    }

}
