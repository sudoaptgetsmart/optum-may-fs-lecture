package net.yorksolutions.myfirstjavaproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

// Controller - is a collection of methods that respond to http requests
// RestController - expects the http requests to follow RESTful guidlines
//     pass data back and forth using JSON format
@RestController // Tell Spring that this class is a rest controller
public class Controller {
    // Tell tomcat to call this method when http://localhost:8080/hello it called with a GET request
    @GetMapping("/hello")
    String helloWorld() {
        return "hello world";
    }

    @GetMapping("/hi")
    String hi() {
        return "hi";
    }

    // URL parameter http://localhost:8080/param?someArg=val1&arg2=val2
    @GetMapping("/param")
    String hi(@RequestParam(name = "someArg") String arg1, String arg2) {
        return "You passed " + arg1 + " and " + arg2;
    }

    // Act as a middle man for ip.jsontest.com
    @GetMapping("/ip")
    String ip() {
        final RestTemplate rest = new RestTemplate();
        return rest.getForObject("http://ip.jsontest.com", String.class);
    }
}
