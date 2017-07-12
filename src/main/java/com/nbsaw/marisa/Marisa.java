package com.nbsaw.marisa;

import com.nbsaw.marisa.server.Server;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Marisa {

    public Marisa(int port){
        start("127.0.0.1",port);
    }

    public Marisa(@NonNull String address, @NonNull int port){
        start(address,port);
    }

    public Marisa(){
        start("127.0.0.1",80);
    }

    public void start(){
        Server.start();
    }

    public void start(@NonNull String address, @NonNull int port){
        Server.start(address,port);
    }

}
