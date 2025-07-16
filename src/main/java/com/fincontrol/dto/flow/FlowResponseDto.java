package com.fincontrol.dto.flow;

import com.fincontrol.model.enums.FlowEnum;

public record FlowResponseDto(String id, String userId, String description, FlowEnum type) {
}
