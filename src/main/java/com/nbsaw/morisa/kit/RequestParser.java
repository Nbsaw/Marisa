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
        HashMap<String, String> params  = new HashMap<>();

        // parse html
        int s = reader.read();
        while (reader.ready()) {
            builder.append((char) s);
            s = reader.read();
        }
        rawHtml = builder.toString();
        // if html content isn't empty
        if (! rawHtml.isEmpty()){
            rawHeader = rawHtml.split("\r\n\r", 2)[0];
            if (rawHtml.split("\r\n\r\n", 2).length > 1) {
                rawBody = rawHtml.split("\r\n\r\n", 2)[1];
            }
            // parse HttpHeader
            String headerLines[] = rawHeader.split("\r\n");
            for (int i = 0; i < headerLines.length; i++) {
                String headerLine = headerLines[i];
                if (i == 0) {
                    // split payLoad to method , router , http version
                    String playLoad[] = headerLine.split(" ");
                    // split router to url and url param
                    String router[] = playLoad[1].split("\\?");
                    headers.put("method", playLoad[0]);
                    headers.put("router", router[0]);
                    headers.put("version", playLoad[2]);
                    // parse url param
                    if (router.length > 1){
                        String[] urlParams = router[1].split("&");
                        for (int j = 0 ; j < urlParams.length ; j++){
                            String sp[] = urlParams[j].split("=",2);
                            if (sp.length > 1){
                                params.put(sp[0],sp[1]);
                            }else{
                                params.put(sp[0],"");
                            }
                        }
                    }
                } else {
                    String[] header = headerLine.split(":", 2);
                    headers.put(header[0], header[1].trim());
                }
            }
            // parse HttpBody
            // 1. Webkit from
            // 2. x-www-form-urlencoded

        }
        return new Request(headers,rawHtml,rawBody,rawHtml,params);
    }
}
