package com.nbsaw.marisa.exception;

public class NotFoundException  extends Exception{

    public NotFoundException(){}

    public NotFoundException(String msg){
        super(msg);
    }

    @Override
    public String getMessage() {
        return "404 not found";
    }

}
