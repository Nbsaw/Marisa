package com.nbsaw.marisa.server;

import com.nbsaw.marisa.http.Request;
import com.nbsaw.morisa.kit.RequestParser;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.io.PrintWriter;
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
        log.info("One client is connect....");
        try {
            Request request = RequestParser.parser(client.getInputStream());
            PrintWriter out = new PrintWriter(client.getOutputStream());
            out.println("HTTP/1.1 200 OK");
            Date now = new Date();
            out.println("Data:" + now);
            out.println("Server: Marisa");
            out.println("Content-Type: text/html; charset=UTF-8");
            out.println();
            out.println("Marisa moe !!");
            out.flush();
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Done ...");
    }
}
