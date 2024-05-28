package org.opcode.exceptions;

public class ArgumentValidationException extends RuntimeException{
    public ArgumentValidationException(String message){
        super(message);
    }
}
