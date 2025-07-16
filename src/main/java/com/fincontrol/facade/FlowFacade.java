package com.fincontrol.facade;

import com.fincontrol.dto.flow.FlowRequestDto;
import com.fincontrol.dto.flow.FlowResponseDto;
import com.fincontrol.dto.flow.FlowUpdateRequestDto;

public interface FlowFacade {
    FlowResponseDto saveFlow(FlowRequestDto flowRequestDto);
    FlowResponseDto updateFlow(FlowUpdateRequestDto flowRequestDto);
}
