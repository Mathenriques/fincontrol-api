package com.fincontrol.facade.implementations;

import com.fincontrol.dto.flow.FlowRequestDto;
import com.fincontrol.dto.flow.FlowResponseDto;
import com.fincontrol.dto.flow.FlowUpdateRequestDto;
import com.fincontrol.facade.FlowFacade;
import com.fincontrol.model.Flow;
import com.fincontrol.service.FlowService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
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

    @Override
    public FlowResponseDto deleteFlow(String flowId) {
        ObjectId userPoid = this.getUserPoid();
        ObjectId id = new ObjectId(flowId);

        Flow deletedFlow = this.flowService.delete(id, userPoid);

        return new FlowResponseDto(
            deletedFlow.getId().toHexString(),
            deletedFlow.getUserId().toHexString(),
            deletedFlow.getDescription(),
            deletedFlow.getType());
    }

    @Override
    public List<FlowResponseDto> getAllFlowsByUserId() {
        ObjectId userPoid = this.getUserPoid();

        List<Flow> flows = this.flowService.getAllFlowsByUser(userPoid);

        return flows.stream()
            .map(flow -> new FlowResponseDto(
                flow.getId().toHexString(),
                flow.getUserId().toHexString(),
                flow.getDescription(),
                flow.getType())
            )
            .collect(Collectors.toList());
    }

    protected ObjectId getUserPoid() {
        log.info("Getting user poid");
        return (ObjectId) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
