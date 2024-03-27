package com.example.codeE.repository;

import com.example.codeE.model.exercise.DataType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataTypeRepository extends MongoRepository<DataType, String> {
}
