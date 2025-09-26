package org.jcr.entidades;
//NO LLEVA CAMBIOS CON LOMBOK
public class CitaException extends Exception {

    public CitaException(String message) {
        super(message);
    }

    public CitaException(String message, Throwable cause) {
        super(message, cause);
    }
}