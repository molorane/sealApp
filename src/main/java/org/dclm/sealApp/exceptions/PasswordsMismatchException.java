package org.dclm.sealApp.exceptions;

import lombok.Getter;

public class PasswordsMismatchException extends RuntimeException {

    @Getter
    private String errormessage;

    public PasswordsMismatchException(String message){
        super(message);
        this.errormessage = message;
    }

    public PasswordsMismatchException(String message, Throwable t){
        super(message,t);
        this.errormessage = message;
    }
}
