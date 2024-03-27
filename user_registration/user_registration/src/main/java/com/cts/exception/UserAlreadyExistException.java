package com.cts.exception;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException(String msg){
        super(msg);
    }

}
