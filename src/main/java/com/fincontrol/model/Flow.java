package com.fincontrol.model;

import com.fincontrol.model.enums.FlowEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "flows")
@AllArgsConstructor
@Getter
@Setter
public class Flow {
    @Id
    private ObjectId id;
    private ObjectId userId;
    private String description;
    private FlowEnum type;
}
