package net.yorksolutions.myfirstjavaproject.json;

import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

public class Headers {
    public Map<String, String> header(
            @RequestHeader Map<String, String> headers) {
        return headers;
    }
}
