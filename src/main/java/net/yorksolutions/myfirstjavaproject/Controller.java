package net.yorksolutions.myfirstjavaproject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

// Assignment

// IP
import javax.servlet.http.HttpServletRequest;

// Date & Time
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// MD5
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Cookie
//import org.json.Cookie;
//import org.json.JSONObject;


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

    private static class IP {
        @JsonProperty("ip")
        String ipAddress;
    }

    // Act as a middle man for ip.jsontest.com
    @GetMapping("/ipjson")
    String ipjson() {
        final RestTemplate rest = new RestTemplate();
        // RestTemplate will assume that the response is JSON formatted, and will try to parse
        //     the json string into the java object that we specify
        final IP ipResponse = rest.getForObject("http://ip.jsontest.com", IP.class);
        return ipResponse.ipAddress;
    }

    private static class Headers {
        @JsonProperty("Host")
        String host;
        @JsonProperty("User-Agent")
        String userAgent;
    }

    @GetMapping("/headers")
    Headers headers() {
        final RestTemplate rest = new RestTemplate();
        return rest.getForObject("http://headers.jsontest.com", Headers.class);
    }


// Assignment

    @GetMapping("/headers2")
    public Map<String, String> header(
            @RequestHeader Map<String, String> headers) {
        return headers;
    }

    @GetMapping("/ip2")
    private static String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED=FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }

    @GetMapping("/date2")
    public String main(String[] args) {
        // Instantiate a Date object
        Date date = new Date();
        // display time and date using toString()
        return (date.toString());
    }

    // TODO needs to be editable by user
    @GetMapping("/echo")
    public HashMap<String, Object> get() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        return map;
    }

    // MD5
    @GetMapping("/md5")
    public static String getMd5(String input) {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // DateTime with Alex
    @GetMapping("/date-time")
    public DateTime dateTime() {
        long currentTime = System.currentTimeMillis();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss SSS");
        String dateResponse = dtf.format(now);
        String myDate = dateResponse.substring(0, 10);
        String myTime = dateResponse.substring(11, 19);

        return (new DateTime(myTime, myDate, currentTime));

    }
}
