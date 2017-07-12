package com.nbsaw.marisa;

import com.nsaw.marisa.banner.Banner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Marisa {
    public Marisa(){
        init();
    }

    public void init(){
        Banner.print();
        // Started Success
        log.info("Marisa is started !!");
        log.info("u can open in http://127.0.0.1:8080");
    }

    public static void main(String[] args) throws InterruptedException {
        new Marisa();
    }
}
