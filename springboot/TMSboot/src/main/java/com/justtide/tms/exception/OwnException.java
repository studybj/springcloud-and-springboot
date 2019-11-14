package com.justtide.tms.exception;

import lombok.Data;

@Data
public class OwnException extends RuntimeException {
    private String message;
    public OwnException(String message){
        this.message = message;
    }

}
