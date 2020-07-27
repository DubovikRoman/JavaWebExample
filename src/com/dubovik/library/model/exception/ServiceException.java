package com.dubovik.library.model.exception;

public class ServiceException extends Exception {

    public ServiceException(){
        super();
    }

    public ServiceException(String e){
        super(e);
    }

    public ServiceException(Throwable throwable){
        super(throwable);
    }

    public  ServiceException(String e, Throwable throwable){
        super(e, throwable);
    }
}