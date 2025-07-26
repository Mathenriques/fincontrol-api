package com.fincontrol.error.flow;

public class FlowAlreadyExistsException extends RuntimeException {
    public FlowAlreadyExistsException() {
        super("Flow with this description already exists");
    }
}
