package com.fincontrol.service;

import com.fincontrol.model.Flow;
import org.bson.types.ObjectId;

import java.util.List;

public interface FlowService {
    Flow save(Flow flow);
    List<Flow> saveFlowList(ObjectId userId, List<Flow> flowList);
    Flow update(Flow newFlowData);
    Flow delete(ObjectId id, ObjectId userId);
    List<Flow> getAllFlowsByUser(ObjectId userId);
}
