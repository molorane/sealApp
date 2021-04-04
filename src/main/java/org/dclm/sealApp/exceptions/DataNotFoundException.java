package org.dclm.sealApp.exceptions;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message){
        super(message);
    }

    public DataNotFoundException(String message, Throwable t){
        super(message,t);
    }

}
