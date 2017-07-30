package br.com.kecyo.userapp.usecases.exception;


public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(){
        super("User Not Found!");
    }
}
