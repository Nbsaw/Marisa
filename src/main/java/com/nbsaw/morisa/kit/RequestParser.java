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
        String rawHeader = "";
        String rawBody = "";
        HashMap<String,String> headers = new HashMap<>();
        if (reader.ready()){
            // parse HttpHeader
            String headerLine = reader.readLine();
            String split[] = headerLine.split(" ");
            headers.put("method"     ,split[0]);
            headers.put("router"     ,split[1]);
            headers.put("httpVersion",split[2]);
            // re readLine
            headerLine = reader.readLine();
            while (headerLine.length() > 0){
                rawHeader += headerLine + "\r\n";
                String[] header = headerLine.split(":",2);
                headers.put(header[0],header[1].trim());
                headerLine = reader.readLine();
            }
//            // parse HttpBody
//            if (reader.ready()){
//                String bodyLine = reader.readLine();
//                while (bodyLine != null && reader.ready()){
//                    rawBody += bodyLine + "\r\n";
//                    System.out.println(bodyLine);
//                    bodyLine = reader.readLine();
//                }
//            }
        }

        headers.forEach((k,v)->{
            System.out.println(rightPad(k,15) + "  :  " + v);
        });

        System.out.println(rawBody);
        return new Request(headers);
    }

    public static String rightPad(String s,int length){
        if (s.length() >= length)
            return s;
        else{
            int l = length - s.length();
            while (l-- > 0){
                s += " ";
            }
            return s;
        }
    }
}
