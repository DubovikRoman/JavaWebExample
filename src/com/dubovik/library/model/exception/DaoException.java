package com.dubovik.library.model.exception;

public class DaoException extends Exception {

    public DaoException(){
        super();
    }

    public DaoException(String e){
        super(e);
    }

    public DaoException(Throwable throwable){
        super(throwable);
    }

    public  DaoException(String e, Throwable throwable){
        super(e, throwable);
    }
}
