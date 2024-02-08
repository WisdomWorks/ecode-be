package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.DataType;
import com.example.codeE.repository.DataTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataTypeImpl implements DataTypeService{
    @Autowired
    private DataTypeRepository dataTypeRepository;
    @Override
    public List<DataType> getAllDataTypes() {
        return dataTypeRepository.findAll();
    }
}
