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
        log.info("Creating flow {}", flow.getDescription());
        Optional<Flow> existingFlow = this.flowRepository.findByUserIdAndDescriptionAndType(flow.getUserId(), flow.getDescription(), flow.getType());
        if (existingFlow.isPresent()) {
            throw new RuntimeException("Already exists this flow");
        }
        this.flowRepository.save(flow);
        return flow;
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
