package net.yorksolutions.myfirstjavaproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
