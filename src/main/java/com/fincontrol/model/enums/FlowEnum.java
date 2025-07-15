package com.fincontrol.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum FlowEnum {
    BASIC_NEEDS("necessidades básicas"),
    LEISURE("lazer"),
    EDUCATION("educação"),
    INCOME("receitas"),
    LONG_TERM("longo prazo"),
    INVESTMENTS("investimentos");

    private final String flow;
}
