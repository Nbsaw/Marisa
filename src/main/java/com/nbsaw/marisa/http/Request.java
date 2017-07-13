package com.nbsaw.marisa.http;

import lombok.Data;

import java.util.HashMap;

@Data
public class Request{
    private HashMap<String,String> headers;
    private String rawHeaders;
    private String rawBody;
    private String rawHtml;

    public Request(HashMap headers){
        this.headers = headers;
    }

    public String getHeader(String name){
        return headers.get(name);
    }

}
