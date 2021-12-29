package com.example.register.config;

public class MyException extends Exception{
    public MyException(){
        super("Hold on,we are currently working on it");
    }
    public MyException(String message){
        super(message);

    }

}
