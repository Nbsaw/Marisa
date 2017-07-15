package com.nbsaw.morisa.kit;

import com.nbsaw.marisa.http.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class RequestParser {
    public static Request parser(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String rawHeader;
        String rawBody = "";
        String rawHtml;
        StringBuilder builder = new StringBuilder();
        HashMap<String, String> headers = new HashMap<>();

        // parse html
        int s = reader.read();
        while (reader.ready()) {
            builder.append((char) s);
            s = reader.read();
        }
        rawHtml = builder.toString();
        if (rawHtml.length() > 0){
            rawHeader = rawHtml.split("\r\n\r", 2)[0];
            if (rawHtml.split("\r\n\r\n", 2).length > 1) {
                rawBody = rawHtml.split("\r\n\r\n", 2)[1];
            }
            // parse HttpHeader
            String headerLines[] = rawHeader.split("\r\n");
            for (int i = 0; i < headerLines.length; i++) {
                String headerLine = headerLines[i];
                if (i == 0) {
                    String split[] = headerLine.split(" ");
                    headers.put("method", split[0]);
                    headers.put("router", split[1]);
                    headers.put("httpVersion", split[2]);
                } else {
                    String[] header = headerLine.split(":", 2);
                    headers.put(header[0], header[1].trim());
                }
            }
            // parse HttpBody
            // 1. Webkit from
            // 2. x-www-form-urlencoded

        }
        return new Request(headers,rawHtml,rawBody,rawHtml);
    }
}
