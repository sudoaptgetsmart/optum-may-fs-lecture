package net.yorksolutions.myfirstjavaproject.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ip {
    @JsonProperty
    public String ip;

    public Ip() {

    }

    @JsonCreator
    public Ip (@JsonProperty String ip) {
        this.ip =ip;
    }
}
