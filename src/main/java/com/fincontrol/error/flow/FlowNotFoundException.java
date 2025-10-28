package com.fincontrol.error.flow;

public class FlowNotFoundException extends RuntimeException {
    public FlowNotFoundException() {
        super("Could not found the flow on Database");
    }
}
