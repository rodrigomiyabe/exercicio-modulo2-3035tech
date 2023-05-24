package br.com.curso3035Tech.modulo2.services.exceptions;

public class PacienteInexistenteException extends RuntimeException{
    public PacienteInexistenteException(String msg){
        super(msg);
    }
}
