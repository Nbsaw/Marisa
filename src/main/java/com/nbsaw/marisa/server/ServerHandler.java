package com.nbsaw.marisa.server;

import com.nbsaw.marisa.exception.NotFoundException;
import com.nbsaw.marisa.env.Environment;
import com.nbsaw.marisa.http.Request;
import com.nbsaw.marisa.http.Response;
import com.nbsaw.morisa.kit.RequestParser;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;

@Slf4j
public class ServerHandler implements Runnable {

    private Socket client;
    
    public ServerHandler(Socket client){
        this.client = client;
    }

    @Override
    public void run() {
        long time = System.currentTimeMillis();
        // Use that can get the process id
        // It not use that , it will show null
        RuntimeMXBean rt = ManagementFactory.getRuntimeMXBean();
        MDC.put("PID", rt.getName().replaceAll("@.*", ""));
        try {
            if (client == null) return;
            // Initialization request and out
            Request  request  = RequestParser.parser(client.getInputStream());
            Response response = new Response(client.getOutputStream());
            // try to handler router ..
            if (!RouterHandler.handlerRouter(request,response)){
                // try to handler router to static file
                if (!RouterHandler.handlerFile(request,response)){
                    // 500 Server Error .
                }
            }
            client.close();
            // if in debug mode , show router execute time
            if (Environment.isDebug()){
                log.debug("{} {} done in {} ms",request.getHeaders().get("method"),request.getHeaders().get("router"),System.currentTimeMillis() - time);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}