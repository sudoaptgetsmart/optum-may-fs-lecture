package net.yorksolutions.myfirstjavaproject.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.yorksolutions.myfirstjavaproject.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @JsonProperty("json")
    String json;

    // Assignment

    HttpServletRequest httpServletRequest;

    void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    JsonController() {
        httpServletRequest = null;
    }

    @GetMapping("/ip2")
    public Ip ip(HttpServletRequest httpServletRequest) {
        if (this.httpServletRequest != null)
            httpServletRequest = this.httpServletRequest;
        return new Ip(httpServletRequest.getRemoteAddr());
    }

    @GetMapping("/headers2")
    Map<String, String> headers(@RequestHeader Map<String, String> headers) {
        return headers;
    }

    @GetMapping("/date2")
    public String main(String[] args) {
        // Instantiate a Date object
        Date date = new Date();
        // display time and date using toString()
        return (date.toString());
    }

    // MD5
//    @GetMapping("/md5")
//    public String to_be_mmd5 = httpServletRequest.getParameter("text");
//    json.put("original",to_be_mmd5);
//
//    try{
//        String md5 = GenerateMD5.generateMD5(to_be_md5);
//        json.put("md5", md5);
//    }
//    catch(RuntimeException e)
//
//    {
//        //A RuntimeException was encountered. The provided String
//        //had an error.
//        json.put("error", "An error was encountered during MD5 hashing. Message: " + e.getMessage());
//        json.put("info", "You must pass a String through the ?text= parameter for a hash to be calculated.");
//    }

//    public static String getMd5(String input) {
//        try {
//            // Static getInstance method is called with hashing MD5
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            // digest() method is called to calculate message digest
//            // of an input digest() return array of byte
//            byte[] messageDigest = md.digest(input.getBytes());
//            // Convert byte array into signum representation
//            BigInteger no = new BigInteger(1, messageDigest);
//
//            // Convert message digest into hex value
//            String hashtext = no.toString(16);
//            while (hashtext.length() < 32) {
//                hashtext = "0" + hashtext;
//            }
//            return hashtext;
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }

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


// TODO needs to be editable by user
//    @GetMapping("/echo")
//    public Map<String, String> echo() {
//        Map<String, String> map = new HashMap<>();
//        String request_uri = httpServletRequest.getRequestURI().substring(1);
//        String[] components = request_uri.split("/");
////        map.put("key1", "value1");
////        return map;
////    }
//
//
//        //Loop through each key/value pair.
//        for (int i = 0; i < components.length; i++) {
//            String key = components[i];
//            String value = "";
//
//            //Try to retrieve the value component.
//            try {
//                value = components[i + 1];
//            } catch (ArrayIndexOutOfBoundsException e) {
//                //If this exception is thrown, that means there are an odd number of tokens
//                //in the request url (in other terms, there is a key value specified, but no
//                //value). It's OK, because we'll just put a blank String into the value component.
//            }
//
//            //Put the key:value component into the JSON object.
//            map.put(key, value);
//
//            //Increase the i variable, because we're looping through
//            //two at a time.
//            i++;
//        }
//        return map;
//    }