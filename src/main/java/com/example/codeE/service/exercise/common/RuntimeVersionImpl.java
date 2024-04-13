package com.example.codeE.service.exercise.common;

import com.example.codeE.model.exercise.common.RuntimeVersion;
import com.example.codeE.repository.RuntimeVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuntimeVersionImpl implements RuntimeVersionService{
    @Autowired
    private RuntimeVersionRepository runtimeVersionRepository;


    @Override
    public void saveRuntimeVersion(RuntimeVersion runtimeVersion) {
        runtimeVersionRepository.save(runtimeVersion);
    }

    @Override
    public void deleteAllRuntimeVersion() {
        runtimeVersionRepository.deleteAll();
    }
}
