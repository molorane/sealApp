package org.dclm.sealApp.exceptions;


import lombok.Getter;

public class DuplicateEntryException extends RuntimeException {

    @Getter
    private String errormessage;

    public DuplicateEntryException(String message){
        super(message);
        this.errormessage = message;
    }

    public DuplicateEntryException(String message, Throwable t){
        super(message,t);
        this.errormessage = message;
    }
}
