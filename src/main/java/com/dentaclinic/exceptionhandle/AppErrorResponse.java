package com.dentaclinic.exceptionhandle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppErrorResponse {

    private int status;
    private String message;
    private long timeStamp;

    public AppErrorResponse() {
    }

    public AppErrorResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
