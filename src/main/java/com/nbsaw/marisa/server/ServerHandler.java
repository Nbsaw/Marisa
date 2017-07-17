package com.nbsaw.marisa.server;

import com.nbsaw.marisa.env.Environment;
import com.nbsaw.marisa.http.Request;
import com.nbsaw.morisa.kit.RequestParser;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.Socket;
import java.util.Date;

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
            Request request = RequestParser.parser(client.getInputStream());
            PrintWriter out = new PrintWriter(client.getOutputStream());
            Date now = new Date();
            out.println("HTTP/1.1 200 OK");
            out.println("Data:" + now);
            out.println("Server: Marisa");
            out.println("Content-Type: text/html; charset=UTF-8");
            out.println();
            out.println("Marisa moe !!");
            out.flush();
            out.close();
            client.close();
            if (Environment.isDebug()){
                log.debug("{} {} done in {} ms",request.getHeaders().get("method"),request.getHeaders().get("router"),System.currentTimeMillis() - time);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
