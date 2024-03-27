package com.example.codeE.model.exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "DataType")
public class DataType {

    @Id
    private String datatypeId;

    @Field
    private String name;

    @Field
    private List<String> dataTypes;

    public DataType(String name, List<String> dataTypes) {
        this.name = name;
        this.dataTypes = dataTypes;
    }
}
