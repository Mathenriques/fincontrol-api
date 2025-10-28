package com.fincontrol.error.flow;

public class FailedToDeleteFlowException extends RuntimeException {
    public FailedToDeleteFlowException(String errorMesage) {
        super("Failed to delete flow from Database: " + errorMesage);
    }
}
