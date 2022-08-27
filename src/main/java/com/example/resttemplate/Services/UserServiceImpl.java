package com.example.resttemplate.Services;

import com.example.resttemplate.Models.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class UserServiceImpl {

    static RestTemplate restTemplate = new RestTemplate();
    public static String JSESSIONID;

    static String url = "http://94.198.50.185:7081/api/users";

    public static String getUsers() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, //
                HttpMethod.GET, entity, String.class);
        UserServiceImpl.JSESSIONID = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        return response.getBody();

    }
    //------------------------------------------------------------------------------------------------------

     public static void addUserByExchangeMethod(String JSESSIONID, User user) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.set(HttpHeaders.COOKIE,JSESSIONID);

        HttpEntity<User> requestEntity  = new HttpEntity<>(user,headers);

        ResponseEntity<User> responseEntity = restTemplate.exchange(url,
                HttpMethod.POST,
                requestEntity,
                User.class);

        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println("status code - " + statusCode);
        User userDetails = responseEntity.getBody();
        System.out.println("response body - " + userDetails);
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        System.out.println("response Headers - " + responseHeaders);
    }


}
