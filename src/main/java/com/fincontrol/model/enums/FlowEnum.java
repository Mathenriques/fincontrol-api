package com.fincontrol.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FlowEnum {
    BASIC_NEEDS("Necessidades Básicas"),
    LEISURE("Lazer"),
    EDUCATION("Educação"),
    INCOME("Receitas"),
    LONG_TERM("Longo Prazo"),
    INVESTMENTS("Investimentos");

    private final String flow;

    @Override
    public String toString() {
        return this.flow;
    }
}
