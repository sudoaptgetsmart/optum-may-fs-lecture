package net.yorksolutions.myfirstjavaproject.json;

import net.yorksolutions.myfirstjavaproject.CacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
public class JsonController {

    final CacheRepository repository;

    // Assignment

    static HttpServletRequest httpServletRequest;

    void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Autowired
    JsonController(CacheRepository repository) {
        this.repository = repository;
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

    // MD5
    @GetMapping("/md5")
    @CrossOrigin
    GenerateMD5 md5(@RequestParam(name = "text") String text) throws NoSuchAlgorithmException {
        Optional<Cache> result = repository.findByInput(text);
        if (result.isPresent()) {
            return result.get().output;
        }
        GenerateMD5 output = new GenerateMD5(text);
        Cache cache = new Cache();
        cache.input = text;
        cache.output = String.valueOf(output);
        repository.save(cache);
        return output;
    }
}


//    NOT USED
//    @GetMapping("/date2")
//    public String main(String[] args) {
//        // Instantiate a Date object
//        Date date = new Date();
//        // display time and date using toString()
//        return (date.toString());
//    }
// ----------------------------------------------------------------

//    public static String to_be_md5 = httpServletRequest.getParameter("text");
//
//    static {
//        MessageDigest md = null;
//        try {
//            md = MessageDigest.getInstance("MD5");
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//        md.update(to_be_md5.getBytes());
//        byte[] digest = md.digest();
//        String myHash = DatatypeConverter
//                .printHexBinary(digest).toUpperCase();

//   ----------------------------------------------------------------------------------
//    public String to_be_md5 = httpServletRequest.getParameter("text");
//
//    {
//        json.put("original", to_be_md5);
//
//        try {
//            String md5 = GenerateMD5.generateMD5(to_be_md5);
//            json.put("md5", md5);
//        } catch (RuntimeException e) {
//            //A RuntimeException was encountered. The provided String
//            //had an error.
//            json.put("error", "An error was encountered during MD5 hashing. Message: " + e.getMessage());
//            json.put("info", "You must pass a String through the ?text= parameter for a hash to be calculated.");
//        }
//    }
//    ----------------------------------------------------------------

//        public static String getMd5(String input){
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
//    }
//    public String md5(String input)  {
//        String md5Hex = DigestUtils.md5Hex(input).toUpperCase();
//        return md5Hex;
//    }
//    public String MD5(String md5) {
//        try {
//            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
//            byte[] array = md.digest(md5.getBytes());
//            StringBuffer sb = new StringBuffer();
//            for (int i = 0; i < array.length; ++i) {
//                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
//            }
//            return sb.toString();
//        } catch (java.security.NoSuchAlgorithmException e) {
//        }
//        return null;
//    }
//    }


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