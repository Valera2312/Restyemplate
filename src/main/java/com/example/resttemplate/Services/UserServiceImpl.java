package com.example.resttemplate.Services;

import com.example.resttemplate.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
//    @Autowired
//    RestTemplate restTemplate;
    static RestTemplate restTemplate = new RestTemplate();

    static String url = "http://94.198.50.185:7081/api/users";
    @Override
    public String getUsers() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

//        ResponseEntity<List<User>> responseEntity =
//                restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
//                });
        ResponseEntity<String> response = restTemplate.exchange(url, //
                HttpMethod.GET, entity, String.class);
        String JSESSIONID = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        addUserByExchangeMethod(JSESSIONID);
        return response.getBody();

    }
    //------------------------------------------------------------------------------------------------------

    private static void addUserByExchangeMethod(String JSESSIONID) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.set(HttpHeaders.COOKIE,JSESSIONID);

        User user = new User();
        user.setId(3L);
        user.setName("James");
        user.setLastName("Brown");
        user.setAge((byte) 29);

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
