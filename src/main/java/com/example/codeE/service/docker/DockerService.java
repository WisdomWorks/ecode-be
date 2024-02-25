package com.example.codeE.service.docker;

import java.io.File;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.transport.DockerHttpClient;

public interface DockerService {

    String buildDockerImage(File dockerFileName);
     String createContainer(String dockerImageIdString);
     String runCmd(String containerId, String... args);
     String convertXmlToJson (String xmlContent);
}
