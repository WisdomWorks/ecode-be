package com.example.codeE.model.exercise.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LanguageDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Language#Language()}
     *   <li>{@link Language#setCommonName(String)}
     *   <li>{@link Language#setDescription(String)}
     *   <li>{@link Language#setExtension(String)}
     *   <li>{@link Language#setKey(String)}
     *   <li>{@link Language#setLanguageId(String)}
     *   <li>{@link Language#setName(String)}
     *   <li>{@link Language#setShortName(String)}
     *   <li>{@link Language#setTemplate(String)}
     *   <li>{@link Language#getCommonName()}
     *   <li>{@link Language#getDescription()}
     *   <li>{@link Language#getExtension()}
     *   <li>{@link Language#getKey()}
     *   <li>{@link Language#getLanguageId()}
     *   <li>{@link Language#getName()}
     *   <li>{@link Language#getShortName()}
     *   <li>{@link Language#getTemplate()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Language actualLanguage = new Language();
        actualLanguage.setCommonName("Common Name");
        actualLanguage.setDescription("The characteristics of someone or something");
        actualLanguage.setExtension("Extension");
        actualLanguage.setKey("Key");
        actualLanguage.setLanguageId("en");
        actualLanguage.setName("Name");
        actualLanguage.setShortName("Short Name");
        actualLanguage.setTemplate("Template");
        String actualCommonName = actualLanguage.getCommonName();
        String actualDescription = actualLanguage.getDescription();
        String actualExtension = actualLanguage.getExtension();
        String actualKey = actualLanguage.getKey();
        String actualLanguageId = actualLanguage.getLanguageId();
        String actualName = actualLanguage.getName();
        String actualShortName = actualLanguage.getShortName();

        // Assert that nothing has changed
        assertEquals("Common Name", actualCommonName);
        assertEquals("Extension", actualExtension);
        assertEquals("Key", actualKey);
        assertEquals("Name", actualName);
        assertEquals("Short Name", actualShortName);
        assertEquals("Template", actualLanguage.getTemplate());
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals("en", actualLanguageId);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link Language#Language(String, String, String, String, String, String, String, String)}
     *   <li>{@link Language#setCommonName(String)}
     *   <li>{@link Language#setDescription(String)}
     *   <li>{@link Language#setExtension(String)}
     *   <li>{@link Language#setKey(String)}
     *   <li>{@link Language#setLanguageId(String)}
     *   <li>{@link Language#setName(String)}
     *   <li>{@link Language#setShortName(String)}
     *   <li>{@link Language#setTemplate(String)}
     *   <li>{@link Language#getCommonName()}
     *   <li>{@link Language#getDescription()}
     *   <li>{@link Language#getExtension()}
     *   <li>{@link Language#getKey()}
     *   <li>{@link Language#getLanguageId()}
     *   <li>{@link Language#getName()}
     *   <li>{@link Language#getShortName()}
     *   <li>{@link Language#getTemplate()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        Language actualLanguage = new Language("en", "Key", "Name", "Short Name", "Common Name", "Template",
                "The characteristics of someone or something", "Extension");
        actualLanguage.setCommonName("Common Name");
        actualLanguage.setDescription("The characteristics of someone or something");
        actualLanguage.setExtension("Extension");
        actualLanguage.setKey("Key");
        actualLanguage.setLanguageId("en");
        actualLanguage.setName("Name");
        actualLanguage.setShortName("Short Name");
        actualLanguage.setTemplate("Template");
        String actualCommonName = actualLanguage.getCommonName();
        String actualDescription = actualLanguage.getDescription();
        String actualExtension = actualLanguage.getExtension();
        String actualKey = actualLanguage.getKey();
        String actualLanguageId = actualLanguage.getLanguageId();
        String actualName = actualLanguage.getName();
        String actualShortName = actualLanguage.getShortName();

        // Assert that nothing has changed
        assertEquals("Common Name", actualCommonName);
        assertEquals("Extension", actualExtension);
        assertEquals("Key", actualKey);
        assertEquals("Name", actualName);
        assertEquals("Short Name", actualShortName);
        assertEquals("Template", actualLanguage.getTemplate());
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals("en", actualLanguageId);
    }
}
