package net.yorksolutions.myfirstjavaproject.json;

import net.yorksolutions.myfirstjavaproject.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class JsonController {

    Headers header;

//    JsonController() {header = new Headers();}

    public JsonController(Headers header) {
        this.header = header;
    }

    public void setHeader(Headers header) {
        this.header = header;
    }

    // Assignment

    HttpServletRequest httpServletRequest;
    void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    JsonController() {
        httpServletRequest = null;
    }

    @GetMapping("/headers2")
    public Object header(){
        return header;
    }
    @GetMapping("/ip2")
    public Ip ip(HttpServletRequest httpServletRequest) {
        if (this.httpServletRequest != null)
            httpServletRequest = this.httpServletRequest;
        return new Ip(httpServletRequest.getRemoteAddr());
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
