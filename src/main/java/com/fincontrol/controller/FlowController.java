package com.fincontrol.controller;

import com.fincontrol.dto.flow.FlowRequestDto;
import com.fincontrol.dto.flow.FlowResponseDto;
import com.fincontrol.dto.flow.FlowUpdateRequestDto;
import com.fincontrol.facade.FlowFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/fincontrol/flows")
public class FlowController {
    private final FlowFacade flowFacade;

    @GetMapping
    public ResponseEntity<List<FlowResponseDto>> getAllByUser() {
        log.info("Calling get all flows by user endpoint");
        return ResponseEntity.status(HttpStatus.OK).body(flowFacade.getAllFlowsByUserId());
    }

    @PostMapping
    public ResponseEntity<FlowResponseDto> create(@RequestBody FlowRequestDto flow) {
        log.info("Calling create flow endpoint");
        return ResponseEntity.status(201).body(flowFacade.saveFlow(flow));
    }

    @PutMapping
    public ResponseEntity<FlowResponseDto> update(@RequestBody FlowUpdateRequestDto flow) {
        log.info("Calling update flow endpoint");
        return ResponseEntity.status(HttpStatus.OK).body(flowFacade.updateFlow(flow));
    }

    @DeleteMapping
    public ResponseEntity<FlowResponseDto> delete(@RequestParam("id") String flowId) {
        log.info("Calling delete flow endpoint");
        return ResponseEntity.status(HttpStatus.OK).body(flowFacade.deleteFlow(flowId));
    }
}
