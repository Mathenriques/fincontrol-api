package com.fincontrol.error.flow;

public class FailedToFindFlowsException extends RuntimeException {
    public FailedToFindFlowsException(String userId, String errorMessage) {
        super("Failed to find flow list from userId: " + userId + " due to error: " + errorMessage);
    }
}
