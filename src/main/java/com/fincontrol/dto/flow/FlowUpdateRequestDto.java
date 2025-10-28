package com.fincontrol.dto.flow;

import com.fincontrol.model.enums.FlowEnum;

public record FlowUpdateRequestDto(String id, String description, FlowEnum type) {
}
