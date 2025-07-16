package com.fincontrol.service;

import com.fincontrol.model.Flow;

public interface FlowService {
    Flow save(Flow flow);
    Flow update(Flow newFlowData);
    void delete();
}
