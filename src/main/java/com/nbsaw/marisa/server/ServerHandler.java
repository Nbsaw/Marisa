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
        // Get the process id
        RuntimeMXBean rt = ManagementFactory.getRuntimeMXBean();
        // MDC
        MDC.put("PID", rt.getName().replaceAll("@.*", ""));

        try {
            if (client == null) return;
            Request request = RequestParser.parser(client.getInputStream());
            Response out = new Response(client.getOutputStream());
            // router manage
            String router =  request.getRouter();
            HashMap<Method,Object> m = Server.getMap.get(router);
            if (m != null){
                m.forEach((k,v)->{
                    try {
                        k.invoke(v,request,out);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
            }
            else if (router.equals("/")){
                out.setHeader();
                out.setContent("Marisa moe !!");
            }else{
                // read file
                try {
                    out.setStatic(router);
                } catch (NotFoundException e) {
                    out.setError();
                    log.warn("{} {} is not found",request.getHeaders().get("method"),request.getHeaders().get("router"));
                }
            }
            client.close();
            if (Environment.isDebug()){
                log.debug("{} {} done in {} ms",request.getHeaders().get("method"),request.getHeaders().get("router"),System.currentTimeMillis() - time);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
