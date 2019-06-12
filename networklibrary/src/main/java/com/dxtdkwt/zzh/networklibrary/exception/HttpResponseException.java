package com.dxtdkwt.zzh.networklibrary.exception;

/**
 * <p>Description:
 *
 * <p>Created by Devin Sun on 2017/3/25.
 */

public class HttpResponseException extends RuntimeException {

    private static final long serialVersionUID = -1089551903254392914L;
    private  int status;

    public HttpResponseException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
