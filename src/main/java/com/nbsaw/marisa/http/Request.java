package com.nbsaw.marisa.http;

import java.util.HashMap;

public class Request{
    private HashMap<String,String> headers;
    private String rawHeaders;
    private String rawBody;
    private String rawHtml;
    private HashMap<String,String> params;

    public Request(HashMap<String,String> headers,
                   String rawHeaders,
                   String rawBody,
                   String rawHtml,
                   HashMap<String,String> params){
        this.headers    = headers;
        this.rawHeaders = rawHeaders;
        this.rawBody    = rawBody;
        this.rawHtml    = rawHtml;
        this.params     = params;
    }

    public String getHeader(String name){
        return headers.get(name);
    }

    public String getRawHeaders() {
        return rawHeaders;
    }

    public String getRawBody() {
        return rawBody;
    }

    public String getRawHtml() {
        return rawHtml;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public String getRouter(){
        return headers.get("router");
    }
}
