package net.yorksolutions.myfirstjavaproject.json;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class JsonController {

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
