package com.jordan.agendaTelefonica.infra.excepition;

public class ValidationExcepition extends RuntimeException {
    public ValidationExcepition(String message) {
        super(message);
    }
}
