package com.nbsaw.marisa.http;

import com.nbsaw.marisa.exception.NotFoundException;
import com.nbsaw.marisa.env.Environment;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

public class Response {

    PrintStream out;

    public Response(OutputStream out){
       this.out  = new PrintStream (out);
    }

    public void setHeader(){
        Date now = new Date();
        out.println("HTTP/1.1 200 OK");
        out.println("Data:" + now);
        out.println("Server: Marisa");
        out.println("Content-Type: text/html; charset=UTF-8");
        out.println();
    }

    public void setContent(String content){
        out.println(content);
        out.flush();
        out.close();
    }

    public void setStatic(String p) throws IOException, NotFoundException {
        File file;
        try{
            file = new File(Environment.CLASS_LOADER.getResource("static" + p).getPath());
        }catch (Exception err){
            throw new NotFoundException();
        }
        Path path = file.toPath();
        FileInputStream  in = new FileInputStream(file);
        // set Header
        Date now = new Date();
        out.println("HTTP/1.1 200 OK");
        out.println("Data: " + now);
        out.println("Server:  Marisa");
        out.println("Content-Type: "+ Files.probeContentType(path));
        out.println("Content-Length: "+ in.available());
        out.println();
        out.write(IOUtils.toByteArray(in));
        out.flush();
        out.close();
    }

    public void setError(){
        Date now = new Date();
        out.println("HTTP/1.1 404 not found");
        out.println("Data:" + now);
        out.println("Server: Marisa");
        out.println("Content-Type: text/html; charset=UTF-8");
        out.println();
        out.println("404 not found");
        out.flush();
        out.close();
    }

    public void redirect(String url){

    }
    public void setCookie(String name,String value){

    }
}
