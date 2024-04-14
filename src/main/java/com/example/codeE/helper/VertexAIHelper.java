package com.example.codeE.helper;

import com.example.codeE.model.exercise.vertexAi.GradingResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.api.GenerationConfig;
import com.google.cloud.vertexai.api.HarmCategory;
import com.google.cloud.vertexai.api.SafetySetting;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class VertexAIHelper {
    @Autowired
    private Environment environment;
    private GenerativeModel generativeModel;

    private ObjectMapper mapper = new ObjectMapper();

    public VertexAIHelper() {
        // Empty constructor - initialization moved to @PostConstruct method
    }

    @PostConstruct
    void init() {
        // This method will be executed after dependency injection is done to perform any initialization
        var projectId = environment.getProperty("google.projectId");
        var location = environment.getProperty("google.location");

        try (VertexAI vertexAi = new VertexAI(projectId, location);) {
            GenerationConfig generationConfig =
                    GenerationConfig.newBuilder()
                            .setMaxOutputTokens(8192)
                            .setTemperature(0F)
                            .setTopP(0F)
                            .build();
            List<SafetySetting> safetySettings = Arrays.asList(
                    SafetySetting.newBuilder()
                            .setCategory(HarmCategory.HARM_CATEGORY_HATE_SPEECH)
                            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                            .build(),
                    SafetySetting.newBuilder()
                            .setCategory(HarmCategory.HARM_CATEGORY_DANGEROUS_CONTENT)
                            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                            .build(),
                    SafetySetting.newBuilder()
                            .setCategory(HarmCategory.HARM_CATEGORY_SEXUALLY_EXPLICIT)
                            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                            .build(),
                    SafetySetting.newBuilder()
                            .setCategory(HarmCategory.HARM_CATEGORY_HARASSMENT)
                            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                            .build()
            );
            this.generativeModel =
                    new GenerativeModel.Builder()
                            .setModelName("gemini-1.0-pro-002")
                            .setVertexAi(vertexAi)
                            .setGenerationConfig(generationConfig)
                            .setSafetySettings(safetySettings)
                            .build();
        }
    }

    public String generateContent(String prompt) {
        try {
            GenerateContentResponse response = generativeModel.generateContent(prompt);
            return ResponseHandler.getText(response);
        } catch (Exception e) {
            return "Error generating content";
        }
    }

    public GradingResponse parseJson(String textResponse) throws JsonProcessingException {
        JsonNode actualObj = mapper.readTree("{" + textResponse);
        String score = actualObj.get("score").asText();
        String comment = actualObj.get("comment").asText();
        return new GradingResponse(Float.parseFloat(score), comment);
    }

    public static String getSingleTestCaseString(int caseNum, String input, String studentOutput, Double casePoint) {
        String promptTemplate = """
                <CASE-%d>
                <INPUT>
                %s\
                
                </INPUT>
                <STUDENT-OUTPUT>
                %s\
                
                </STUDENT-OUTPUT>
                <CASE-POINT>%f</CASE-POINT>
                </CASE-%d>\n""";
        return String.format(promptTemplate, caseNum, input, studentOutput, casePoint, caseNum);
    }
}
