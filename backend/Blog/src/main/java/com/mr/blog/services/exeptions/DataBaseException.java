package com.mr.blog.services.exeptions;

public class DataBaseException extends RuntimeException{
    public DataBaseException(String message) {
        super(message);
    }
}
