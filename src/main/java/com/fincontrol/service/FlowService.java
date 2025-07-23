package com.fincontrol.service;

import com.fincontrol.model.Flow;
import org.bson.types.ObjectId;

public interface FlowService {
    Flow save(Flow flow);
    Flow update(Flow newFlowData);
    Flow delete(ObjectId id, ObjectId userId);
}
