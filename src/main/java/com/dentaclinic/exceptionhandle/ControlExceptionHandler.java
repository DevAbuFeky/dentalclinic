package com.dentaclinic.exceptionhandle;

public class ControlExceptionHandler extends RuntimeException{

    public ControlExceptionHandler(String message) {
        super(message);
    }

    public ControlExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public ControlExceptionHandler(Throwable cause) {
        super(cause);
    }


}
