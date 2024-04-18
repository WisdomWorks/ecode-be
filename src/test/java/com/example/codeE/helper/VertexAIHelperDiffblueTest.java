package com.example.codeE.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {VertexAIHelper.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@DisabledInAotMode
class VertexAIHelperDiffblueTest {
    @Autowired
    private VertexAIHelper vertexAIHelper;

    @MockBean
    private Environment environment;

    /**
     * Method under test: {@link VertexAIHelper#init()}
     */
    @Test
    void testInit() {
        // Arrange
        when(environment.getProperty(Mockito.<String>any())).thenReturn("Property");

        // Act
        vertexAIHelper.init();

        // Assert
        verify(environment, atLeast(1)).getProperty(Mockito.<String>any());
    }

    /**
     * Method under test: {@link VertexAIHelper#generateContent(String)}
     */
    @Test
    void testGenerateContent() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange, Act and Assert
        assertEquals("Error generating content", (new VertexAIHelper()).generateContent("Prompt"));
    }

    /**
     * Method under test: {@link VertexAIHelper#parseJson(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testParseJson() throws JsonProcessingException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.core.JsonParseException: Unexpected character ('T' (code 84)): was expecting double-quote to start field name
        //    at [Source: (String)"{Text Response"; line: 1, column: 3]
        //       at com.fasterxml.jackson.core.JsonParser._constructError(JsonParser.java:2477)
        //       at com.fasterxml.jackson.core.base.ParserMinimalBase._reportError(ParserMinimalBase.java:750)
        //       at com.fasterxml.jackson.core.base.ParserMinimalBase._reportUnexpectedChar(ParserMinimalBase.java:674)
        //       at com.fasterxml.jackson.core.json.ReaderBasedJsonParser._handleOddName(ReaderBasedJsonParser.java:1940)
        //       at com.fasterxml.jackson.core.json.ReaderBasedJsonParser.nextFieldName(ReaderBasedJsonParser.java:968)
        //       at com.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer._deserializeContainerNoRecursion(JsonNodeDeserializer.java:536)
        //       at com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer.deserialize(JsonNodeDeserializer.java:100)
        //       at com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer.deserialize(JsonNodeDeserializer.java:25)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectMapper._readTreeAndClose(ObjectMapper.java:4867)
        //       at com.fasterxml.jackson.databind.ObjectMapper.readTree(ObjectMapper.java:3219)
        //       at com.example.codeE.helper.VertexAIHelper.parseJson(VertexAIHelper.java:85)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        vertexAIHelper.parseJson("Text Response");
    }

    /**
     * Method under test:
     * {@link VertexAIHelper#getSingleTestCaseString(int, String, String, Double)}
     */
    @Test
    void testGetSingleTestCaseString() {
        // Arrange, Act and Assert
        assertEquals(
                "<CASE-10>\n" + "<INPUT>\n" + "Input\n" + "</INPUT>\n" + "<STUDENT-OUTPUT>\n" + "Student Output\n"
                        + "</STUDENT-OUTPUT>\n" + "<CASE-POINT>10.000000</CASE-POINT>\n" + "</CASE-10>\n",
                VertexAIHelper.getSingleTestCaseString(10, "Input", "Student Output", 10.0d));
    }
}
