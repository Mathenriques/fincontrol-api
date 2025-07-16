package com.fincontrol.model;

import com.fincontrol.model.enums.FlowEnum;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "flows")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Flow {
    @Id
    private ObjectId id;
    private ObjectId userId;
    private String description;
    private FlowEnum type;

    public Flow(ObjectId userId, String description, FlowEnum type) {
        this.userId = userId;
        this.description = description;
        this.type = type;
    }
}
