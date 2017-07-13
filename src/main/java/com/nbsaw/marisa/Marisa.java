package com.nbsaw.marisa;

import com.nbsaw.marisa.env.Environment;
import com.nbsaw.marisa.server.Server;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Marisa {

    private String port = Environment.config.getProperty("server.port","3000");
    private String address = Environment.config.getProperty("server.address","0.0.0.0");

    public Marisa(@NonNull String port){
        start(address,port);
    }

    public Marisa(@NonNull String address, @NonNull String port){
        start(address,port);
    }

    public Marisa(){
        start(address,port);
    }

    public void start() {
        Server.start(address,port);
    }

    public void start(@NonNull String address, @NonNull String port){
        Server.start(address,port);
    }

}
