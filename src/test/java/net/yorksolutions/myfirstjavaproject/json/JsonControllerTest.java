package net.yorksolutions.myfirstjavaproject.json;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
class JsonControllerTest {

    @LocalServerPort
    int port;

    @Mock
    Headers header;

    @Autowired
    JsonController controller;

    @Test
    void itShouldCallHeadersAndReturnItsValue() {
        when(header).thenReturn("it was called");
        controller.setHeader(header);
        final RestTemplate rest = new RestTemplate();
    }

    @Mock
    Ip ip;

    @Mock
    HttpServletRequest mockRequest;

    @Test
    void itShouldCallIpAndReturnItsValue() {
        controller.setHttpServletRequest(mockRequest);
        when(mockRequest.getRemoteAddr()).thenReturn("it was called");
        final RestTemplate rest = new RestTemplate();
        final ResponseEntity<Ip> actual = rest.getForEntity("http://localhost" + port + "/ip2", Ip.class);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals("it was called", actual.getBody().ip);
    }

    @Test
    void get() {
    }

    @Test
    void getMd5() {
    }

    @Test
    void dateTime() {
    }
}