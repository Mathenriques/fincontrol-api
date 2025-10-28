package com.fincontrol.facade;

import com.fincontrol.dto.flow.*;
import com.fincontrol.model.Flow;

import java.util.List;

public interface FlowFacade {
    FlowResponseDto saveFlow(FlowRequestDto flowRequestDto);
    List<Flow> savePredefinedFlowsIfUserHasNone();
    List<Flow> saveCustomFlows(List<Flow> flowList);
    FlowResponseDto updateFlow(FlowUpdateRequestDto flowRequestDto);
    FlowResponseDto deleteFlow(String flowId);
    List<FlowResponseDto> getAllFlowsByUserId();
}
