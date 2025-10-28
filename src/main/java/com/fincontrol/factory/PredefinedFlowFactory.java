package com.fincontrol.factory;

import com.fincontrol.model.Flow;
import com.fincontrol.model.enums.FlowEnum;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class PredefinedFlowFactory {

    private static final List<PredefinedFlow> predefinedFlows = List.of(
            new PredefinedFlow("Água", FlowEnum.BASIC_NEEDS),
            new PredefinedFlow("Aluguel", FlowEnum.BASIC_NEEDS),
            new PredefinedFlow("Parcelamento do Carro", FlowEnum.BASIC_NEEDS),
            new PredefinedFlow("Seguro de vida", FlowEnum.BASIC_NEEDS),
            new PredefinedFlow("Supermercado", FlowEnum.BASIC_NEEDS),
            new PredefinedFlow("Cartão de Crédito", FlowEnum.BASIC_NEEDS),
            new PredefinedFlow("Celular", FlowEnum.BASIC_NEEDS),
            new PredefinedFlow("Codomínio", FlowEnum.BASIC_NEEDS),
            new PredefinedFlow("Escolas (filhos)", FlowEnum.BASIC_NEEDS),
            new PredefinedFlow("Internet", FlowEnum.BASIC_NEEDS),
            new PredefinedFlow("Energia", FlowEnum.BASIC_NEEDS),
            new PredefinedFlow("Saúde", FlowEnum.BASIC_NEEDS),
            new PredefinedFlow("Plano de saúde", FlowEnum.BASIC_NEEDS),
            new PredefinedFlow("Transporte", FlowEnum.BASIC_NEEDS),
            new PredefinedFlow("Empréstimo", FlowEnum.BASIC_NEEDS),
            new PredefinedFlow("Extras (Casa)", FlowEnum.BASIC_NEEDS),
            new PredefinedFlow("Outros (Necessidades básicas)", FlowEnum.BASIC_NEEDS),
            new PredefinedFlow("Suplementação", FlowEnum.BASIC_NEEDS),
            new PredefinedFlow("Seguro do carro", FlowEnum.BASIC_NEEDS),

            new PredefinedFlow("Alimentação (Gastos extras)", FlowEnum.LEISURE),
            new PredefinedFlow("Assinaturas Mensais", FlowEnum.LEISURE),
            new PredefinedFlow("Entretenimento mensal", FlowEnum.LEISURE),
            new PredefinedFlow("Outros (lazer)", FlowEnum.LEISURE),
            new PredefinedFlow("Corrida", FlowEnum.LEISURE),
            new PredefinedFlow("Viagem", FlowEnum.LEISURE),

            new PredefinedFlow("Educação", FlowEnum.EDUCATION),
            new PredefinedFlow("Longo prazo", FlowEnum.LONG_TERM),
            new PredefinedFlow("Liberdade Financeira", FlowEnum.INVESTMENTS),
            new PredefinedFlow("Reserva de Emergência", FlowEnum.INVESTMENTS),
            new PredefinedFlow("Outros (Renda)", FlowEnum.INCOME),
            new PredefinedFlow("Salário", FlowEnum.INCOME)
    );

    public List<Flow> createPredefinedFlowsForUser(ObjectId userId) {
        log.info("Creating pré-defined flows");
        return predefinedFlows.stream()
                .map(pf -> new Flow(userId, pf.description(), pf.type()))
                .collect(Collectors.toList());
    }

    public List<PredefinedFlow> getAvailableTemplates() {
        return predefinedFlows;
    }

    public record PredefinedFlow(String description, FlowEnum type) {}
}

