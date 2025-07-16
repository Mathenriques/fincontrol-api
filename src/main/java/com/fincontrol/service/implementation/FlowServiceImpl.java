package com.fincontrol.service.implementation;

import com.fincontrol.model.Flow;
import com.fincontrol.repository.FlowRepository;
import com.fincontrol.service.FlowService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class FlowServiceImpl implements FlowService {
    private final FlowRepository flowRepository;

    @Override
    public Flow save(Flow flow) {
        log.info("Creating flow: {}", flow);

        Optional<Flow> existingFlow = this.flowRepository.findByUserIdAndDescriptionAndType(flow.getUserId(), flow.getDescription(), flow.getType());

        if (existingFlow.isPresent()) {
            throw new RuntimeException("Already exists this flow");
        }

        this.flowRepository.save(flow);
        log.info("Flow {} created", flow);
        return flow;
    }

    @Override
    public Flow update(Flow newFlowData) {
        Optional<Flow> existsFlow = this.flowRepository.findById(newFlowData.getId());

        if (existsFlow.isEmpty()) {
            log.error("Flow not found");
            throw new RuntimeException("Flow not found");
        }

        Flow flow = existsFlow.get();

        if (!newFlowData.getUserId().toHexString().equals(flow.getUserId().toHexString())) {
            log.error("This flow do not belongs to this user. Flow owner: {} while user: {}",
                    newFlowData.getUserId().toHexString(),
                    flow.getUserId().toHexString());
            throw new RuntimeException("This flow does not belongs to this user");
        }


        flow.setDescription(newFlowData.getDescription());
        flow.setType(newFlowData.getType());

        try {
            this.flowRepository.save(flow);
        } catch (Exception e) {
            log.error("Failed to save flow, due to: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to save flow");
        }

        return flow;

    }

    @Override
    public void delete() {

    }
}
