package com.nbsaw.marisa;

import com.nbsaw.marisa.env.Environment;
import com.nbsaw.marisa.server.Server;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

@Slf4j
public class Marisa {

    private String port = Environment.config.getProperty("server.port","3000");

    private String address = Environment.config.getProperty("server.address","0.0.0.0");

    public Marisa(Class bootClass,@NonNull String port){
        start(address,port,bootClass);
    }

    public Marisa(Class bootClass,@NonNull String address, @NonNull String port){
        start(address,port,bootClass);
    }

    public Marisa(Class bootClass){
        start(address,port,bootClass);
    }

    public void start(Class bootClass) {
        Server.start(address,port,bootClass);
    }

    public void start(@NonNull String address, @NonNull String port,Class bootClass){

        // Get the process id
        RuntimeMXBean rt = ManagementFactory.getRuntimeMXBean();

        // MDC
        MDC.put("PID", rt.getName().replaceAll("@.*", ""));

        Server.start(address,port,bootClass);
    }

}
