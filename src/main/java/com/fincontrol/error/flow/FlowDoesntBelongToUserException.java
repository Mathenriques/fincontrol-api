package com.fincontrol.error.flow;

public class FlowDoesntBelongToUserException extends RuntimeException {
    public FlowDoesntBelongToUserException(String userId) {
        super("Flow does not belongs to the user: " + userId);
    }
}
