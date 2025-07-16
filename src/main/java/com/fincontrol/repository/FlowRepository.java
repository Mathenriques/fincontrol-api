package com.fincontrol.repository;

import com.fincontrol.model.Flow;
import com.fincontrol.model.enums.FlowEnum;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FlowRepository extends MongoRepository<Flow, ObjectId> {
    Optional<Flow> findByUserIdAndDescriptionAndType(ObjectId userId, String description, FlowEnum type);
}
