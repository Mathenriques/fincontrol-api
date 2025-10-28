package com.fincontrol.error.flow;

public class FailedToSaveFlowException extends RuntimeException {
    public FailedToSaveFlowException(String message) {
        super("Failed to save flow, due to error: " + message);
    }
}
