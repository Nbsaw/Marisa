package com.nbsaw.marisa.http;

import lombok.Data;

import java.util.HashMap;

@Data
public class Request{
    private HashMap<String,String> headers;
    private String rawHeaders;
    private String rawBody;
    private String rawHtml;

    public Request(HashMap headers, String rawHeaders, String rawBody, String rawHtml){
        this.headers = headers;
        this.rawHeaders = rawHeaders;
        this.rawBody = rawBody;
        this.rawHtml = rawHtml;
    }

    public String getHeader(String name){
        return headers.get(name);
    }

}
