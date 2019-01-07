package controller;


import application.Application;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractControllerIntegrationTest {


    @LocalServerPort
    private int port;

    TestRestTemplate testRestTemplate;
    HttpHeaders httpHeaders;


    public AbstractControllerIntegrationTest() {


        this.httpHeaders = new HttpHeaders();
        this.httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");

        testRestTemplate = new TestRestTemplate(new RestTemplateBuilder()
                .setConnectTimeout(5000)
                .setReadTimeout(5000));

    }

    protected String HTTP_GET(String url) {
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, httpHeaders);

        ResponseEntity<String> response = testRestTemplate.exchange(createURLWithPort(url), HttpMethod.GET, httpEntity, String.class);

        return response.getBody();
    }

    private String createURLWithPort(String url) {

        if (url.startsWith("http")) {
            return url;
        }
        return "http://localhost:" + port + url;
    }


    protected String HTTP_POST(String url, Object object) {
        return httpRequest(url, object, HttpMethod.POST);
    }

    private String httpRequest(String url, Object object, HttpMethod method) {

        try {
            ObjectMapper mapper = new ObjectMapper();

            HttpEntity<String> httpEntity = new HttpEntity<String>(mapper.writeValueAsString(object), httpHeaders);

            ResponseEntity<String> responseEntity = testRestTemplate.exchange(createURLWithPort(url), method, httpEntity, String.class);

            return responseEntity.getBody();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected String HTTP_PUT(String url, Object object){
        return httpRequest(url, object, HttpMethod.PUT);
    }
}
