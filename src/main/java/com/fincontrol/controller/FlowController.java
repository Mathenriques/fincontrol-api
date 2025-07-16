package com.fincontrol.controller;

import com.fincontrol.dto.flow.FlowRequestDto;
import com.fincontrol.dto.flow.FlowResponseDto;
import com.fincontrol.dto.flow.FlowUpdateRequestDto;
import com.fincontrol.facade.FlowFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/fincontrol/flows")
public class FlowController {
    private final FlowFacade flowFacade;

    @PostMapping
    public ResponseEntity<FlowResponseDto> create(@RequestBody FlowRequestDto flow) {
        return ResponseEntity.status(201).body(flowFacade.saveFlow(flow));
    }

    @PutMapping
    public ResponseEntity<FlowResponseDto> update(@RequestBody FlowUpdateRequestDto flow) {
        return ResponseEntity.status(200).body(flowFacade.updateFlow(flow));
    }
}
