package net.yorksolutions.myfirstjavaproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FizzBuzzControllerTest {
    @LocalServerPort
    int port;

    @Test
    void itShouldCallFizzBuzzAndReturnItsValue() {
        final RestTemplate rest = new RestTemplate();
        final ResponseEntity<String> actual = rest.getForEntity("http://localhost:" + port + "/fizzbuzz?input=4", String.class);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals("4", actual.getBody());
        final ResponseEntity<String> actual7 = rest.getForEntity("http://localhost:" + port + "/fizzbuzz?input=7", String.class);
        assertEquals(HttpStatus.OK, actual7.getStatusCode());
        assertEquals("7", actual7.getBody());
    }
}