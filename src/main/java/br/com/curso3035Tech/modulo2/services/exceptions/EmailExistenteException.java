package br.com.curso3035Tech.modulo2.services.exceptions;

public class EmailExistenteException extends RuntimeException{

    public EmailExistenteException(String msg){
        super(msg);
    }
}
