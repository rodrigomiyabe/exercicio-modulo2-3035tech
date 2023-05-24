package br.com.curso3035Tech.modulo2.services.exceptions;

public class MedicoInexistenteException extends RuntimeException{
    public MedicoInexistenteException(String msg){
        super(msg);
    }
}
