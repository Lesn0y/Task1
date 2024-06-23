package rest;

import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CookieManager implements ClientHttpRequestInterceptor {

    private final List<HttpCookie> cookieStore = new ArrayList<>();

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (!cookieStore.isEmpty()) {
            for (HttpCookie cookie : cookieStore) {
                request.getHeaders().add(HttpHeaders.COOKIE, cookie.toString());
            }
        }

        ClientHttpResponse response = execution.execute(request, body);

        List<String> setCookieHeaders = response.getHeaders().get(HttpHeaders.SET_COOKIE);
        if (setCookieHeaders != null) {
            for (String cookie : setCookieHeaders) {
                String name = cookie.substring(0, cookie.indexOf("="));
                String value = cookie.substring(cookie.indexOf("=") + 1);
                HttpCookie httpCookie = new HttpCookie(name, value);
                cookieStore.add(httpCookie);
            }
        }
        return response;
    }
}
