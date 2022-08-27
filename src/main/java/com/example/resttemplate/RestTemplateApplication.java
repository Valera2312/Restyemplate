package com.example.resttemplate;

import com.example.resttemplate.Models.User;
import com.example.resttemplate.Services.UserServiceImpl;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestTemplateApplication {

    public static void main(String[] args)  {
        SpringApplication.run(RestTemplateApplication.class, args);

        User user = new User();
        user.setId(3L);
        user.setName("James");
        user.setLastName("Brown");
        user.setAge((byte) 29);

        UserServiceImpl.getUsers();

        System.out.print(UserServiceImpl.addUserByExchangeMethod(user));

        User user_update = new User();
        user_update.setId(3L);
        user_update.setName("Thomas");
        user_update.setLastName("Shelby");
        user_update.setAge((byte) 29);

        System.out.print(UserServiceImpl.PutWithExchangeExample(user_update));

        System.out.print(UserServiceImpl.Delete());


    }
}
