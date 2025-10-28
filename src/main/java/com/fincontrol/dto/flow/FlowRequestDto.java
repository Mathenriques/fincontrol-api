package com.fincontrol.dto.flow;

import com.fincontrol.model.enums.FlowEnum;

public record FlowRequestDto(String description, FlowEnum type) {}
