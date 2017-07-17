package com.nbsaw.marisa.http;

import java.io.OutputStream;
import java.io.PrintWriter;

public class Response {
    PrintWriter out;
    public Response(OutputStream out){
       this.out  = new PrintWriter(out);
    }


}
