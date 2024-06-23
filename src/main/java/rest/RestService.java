package rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import rest.dto.User;

public class RestService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://94.198.50.185:7081/api/users";

    public RestService() {
        restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new CookieManager());
    }

    public User[] getAllUsers() {
        return restTemplate
                .getForObject(BASE_URL, User[].class);
    }

    public String saveUser(User user) {
        return restTemplate.postForObject(BASE_URL, user, String.class);
    }

    public String updateUser(User user) {
        return restTemplate.exchange(BASE_URL, HttpMethod.PUT, new HttpEntity<>(user) ,String.class).getBody();
    }

    public String deleteUser(long id) {
        return restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.DELETE, null, String.class).getBody();
    }
}
