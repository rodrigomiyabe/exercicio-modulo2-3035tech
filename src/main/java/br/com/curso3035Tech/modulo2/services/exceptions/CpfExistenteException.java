package br.com.curso3035Tech.modulo2.services.exceptions;

public class CpfExistenteException extends RuntimeException{

    public CpfExistenteException(String msg){
        super(msg);
    }

}
