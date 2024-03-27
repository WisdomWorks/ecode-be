package com.example.codeE.service.exercise.common;

import com.example.codeE.model.exercise.common.RuntimeVersion;

public interface RuntimeVersionService {
    public void saveRuntimeVersion(RuntimeVersion runtimeVersion);
    public void deleteAllRuntimeVersion();
}
