package com.example.resttemplate.Services;

import com.example.resttemplate.Models.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.List;

public class UserServiceImpl {

    static RestTemplate restTemplate = new RestTemplate();
    public static String JSESSIONID;

    static String url = "http://94.198.50.185:7081/api/users";


    public static HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.set(HttpHeaders.COOKIE, JSESSIONID);
        return headers;
    }
    public static void getUsers() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, //
                HttpMethod.GET, entity, String.class);
        UserServiceImpl.JSESSIONID = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
       
    }
    //------------------------------------------------------------------------------------------------------

    public static String addUserByExchangeMethod(User user) {



        HttpEntity<User> requestBody = new HttpEntity<>(user, setHeaders());
        // Send request with POST method.
        return restTemplate.postForEntity(url, requestBody, String.class).getBody();
    }

    public static String PutWithExchangeExample(User user) {


        // Data attached to the request.
        HttpEntity<User> requestBody = new HttpEntity<>(user, setHeaders());

        // Send request with PUT method.
        return restTemplate.exchange(url, HttpMethod.PUT, requestBody, String.class).getBody();
    }

    public static String Delete() {

        // URL with URI-variable
        String resourceUrl = "http://94.198.50.185:7081/api/users/3 ";

        HttpEntity<User> requestBody = new HttpEntity<>(setHeaders());
        // Send request with DELETE method.
        return restTemplate.exchange(resourceUrl, HttpMethod.DELETE, requestBody, String.class).getBody();

    }
}








