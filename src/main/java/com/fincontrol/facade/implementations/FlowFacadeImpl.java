package com.fincontrol.facade.implementations;

import com.fincontrol.dto.flow.FlowRequestDto;
import com.fincontrol.dto.flow.FlowResponseDto;
import com.fincontrol.dto.flow.FlowUpdateRequestDto;
import com.fincontrol.facade.FlowFacade;
import com.fincontrol.model.Flow;
import com.fincontrol.service.FlowService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FlowFacadeImpl implements FlowFacade {

    private final FlowService flowService;

    @Override
    public FlowResponseDto saveFlow(FlowRequestDto flowRequestDto) {
        ObjectId userPoid = this.getUserPoid();

        Flow flow = new Flow(userPoid, flowRequestDto.description(), flowRequestDto.type());

        Flow savedFlow = this.flowService.save(flow);

        return new FlowResponseDto(
                savedFlow.getId().toHexString(),
                savedFlow.getUserId().toHexString(),
                savedFlow.getDescription(),
                savedFlow.getType());
    }

    @Override
    public FlowResponseDto updateFlow(FlowUpdateRequestDto flowRequestDto) {
        ObjectId userPoid = this.getUserPoid();
        ObjectId flowId = new ObjectId(flowRequestDto.id());

        Flow flow = new Flow(flowId, userPoid, flowRequestDto.description(), flowRequestDto.type());

        Flow updatedFlow = this.flowService.update(flow);

        return new FlowResponseDto(
                updatedFlow.getId().toHexString(),
                updatedFlow.getUserId().toHexString(),
                updatedFlow.getDescription(),
                updatedFlow.getType());
    }

    protected ObjectId getUserPoid() {
        return (ObjectId) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
