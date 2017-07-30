package br.com.kecyo.userapp.usescases.exception;


public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(){
        super("User Not Found!");
    }
}
