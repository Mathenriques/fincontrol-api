package com.fincontrol.service.implementation;

import com.fincontrol.error.flow.*;
import com.fincontrol.factory.PredefinedFlowFactory;
import com.fincontrol.model.Flow;
import com.fincontrol.repository.FlowRepository;
import com.fincontrol.service.FlowService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class FlowServiceImpl implements FlowService {
    private final FlowRepository flowRepository;
    private final PredefinedFlowFactory predefinedFlowFactory;

    @Override
    public Flow save(Flow flow) {
        log.info("Checking if flow: {} already exists", flow.toString());
        Optional<Flow> existingFlow = this.flowRepository.findByUserIdAndDescriptionAndType(
            flow.getUserId(),
            flow.getDescription(),
            flow.getType());

        if (existingFlow.isPresent()) {
            log.error("Flow already exists: {}", existingFlow);
            throw new FlowAlreadyExistsException();
        }

        try {
            log.info("Saving flow on database");
            this.flowRepository.save(flow);
        } catch (final Exception e) {
            log.error("Failed to save flow due to: {}", e.getMessage(), e);
            throw new FailedToSaveFlowException(e.getMessage());
        }
        log.info("Flow {} created", flow);
        return flow;
    }

    @Override
    public List<Flow> saveFlowList(ObjectId userId, List<Flow> flowList) {
        try {
            log.info("Saving flow list");
            flowRepository.saveAll(flowList);
        } catch (final Exception e) {
            log.error("Failed to save flows due to: {}", e.getMessage(), e);
            throw new FailedToSaveFlowException(e.getMessage());
        }

        return flowList;
    }

    @Override
    public Flow update(Flow newFlowData) {
        String userId = newFlowData.getUserId().toHexString();

        Flow flow = this.checkIfFlowExistsById(newFlowData.getId());

        if (!userId.equals(flow.getUserId().toHexString())) {
            log.error("This flow do not belongs to this user. Flow owner: {} while user: {}",
                    userId,
                    flow.getUserId().toHexString());
            throw new FlowDoesntBelongToUserException(userId);
        }

        flow.setDescription(newFlowData.getDescription());
        flow.setType(newFlowData.getType());

        try {
            log.info("Saving flow with new data: {}", flow);
            this.flowRepository.save(flow);
        } catch (Exception e) {
            log.error("Failed to save flow, due to: {}", e.getMessage(), e);
            throw new FailedToSaveFlowException(e.getMessage());
        }

        return flow;

    }

    @Override
    public Flow delete(ObjectId id, ObjectId userId) {
        Flow flow = this.checkIfFlowExistsById(id);

        log.info("Flow with id {} exists. Validating now if belongs to user {}", id, userId);
        if (!flow.getUserId().equals(userId)) {
            log.error("Flow does not belong to this user");
            throw new FlowDoesntBelongToUserException(userId.toHexString());
        }

        try {
            log.info("Trying to delete flow: {}", id);
            this.flowRepository.deleteById(id);
        } catch (final Exception e) {
            log.error("Failed to delete flow by id {} from database", id.toHexString(), e);
            throw new FailedToDeleteFlowException(e.getMessage());
        }

        return flow;
    }

    @Override
    public List<Flow> getAllFlowsByUser(ObjectId userId) {
        log.info("Finding for user {} flows", userId);
        List<Flow> flows;

        try {
            flows = this.flowRepository.findByUserId(userId);
        } catch (final Exception e) {
            log.error("Failed to find flow list from userId {}", userId.toHexString(), e);
            throw new FailedToFindFlowsException(userId.toHexString(), e.getMessage());
        }
        return flows;
    }

    private Flow checkIfFlowExistsById(ObjectId id) {
        log.info("Checking if flow with id: {} exists", id.toHexString());
        Optional<Flow> existsFlow = this.flowRepository.findById(id);

        if (existsFlow.isEmpty()) {
            log.error("Could not found flow id {} on Database", id.toHexString());
            throw new FlowNotFoundException();
        }

        return existsFlow.get();
    }
}
