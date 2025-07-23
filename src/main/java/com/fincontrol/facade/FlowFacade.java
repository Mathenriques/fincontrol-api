package com.fincontrol.facade;

import com.fincontrol.dto.flow.FlowRequestDto;
import com.fincontrol.dto.flow.FlowResponseDto;
import com.fincontrol.dto.flow.FlowUpdateRequestDto;
import org.bson.types.ObjectId;

public interface FlowFacade {
    FlowResponseDto saveFlow(FlowRequestDto flowRequestDto);
    FlowResponseDto updateFlow(FlowUpdateRequestDto flowRequestDto);
    FlowResponseDto deleteFlow(String flowId);
}
