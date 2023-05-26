package br.com.curso3035Tech.modulo2.services.exceptions;

import java.io.Serial;

public class EntityAlreadyExists extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public EntityAlreadyExists (String msg){
        super(msg);
    }
}
