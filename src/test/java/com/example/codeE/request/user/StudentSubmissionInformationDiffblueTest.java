package com.example.codeE.request.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StudentSubmissionInformationDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link StudentSubmissionInformation#StudentSubmissionInformation()}
     *   <li>{@link StudentSubmissionInformation#setDateSubmit(String)}
     *   <li>{@link StudentSubmissionInformation#setGrade(float)}
     *   <li>{@link StudentSubmissionInformation#setReviewable(boolean)}
     *   <li>{@link StudentSubmissionInformation#setUserId(String)}
     *   <li>{@link StudentSubmissionInformation#setUserName(String)}
     *   <li>{@link StudentSubmissionInformation#getDateSubmit()}
     *   <li>{@link StudentSubmissionInformation#getGrade()}
     *   <li>{@link StudentSubmissionInformation#getUserId()}
     *   <li>{@link StudentSubmissionInformation#getUserName()}
     *   <li>{@link StudentSubmissionInformation#isReviewable()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        StudentSubmissionInformation actualStudentSubmissionInformation = new StudentSubmissionInformation();
        actualStudentSubmissionInformation.setDateSubmit("2020-03-01");
        actualStudentSubmissionInformation.setGrade(10.0f);
        actualStudentSubmissionInformation.setReviewable(true);
        actualStudentSubmissionInformation.setUserId("42");
        actualStudentSubmissionInformation.setUserName("janedoe");
        String actualDateSubmit = actualStudentSubmissionInformation.getDateSubmit();
        float actualGrade = actualStudentSubmissionInformation.getGrade();
        String actualUserId = actualStudentSubmissionInformation.getUserId();
        String actualUserName = actualStudentSubmissionInformation.getUserName();

        // Assert that nothing has changed
        assertEquals("2020-03-01", actualDateSubmit);
        assertEquals("42", actualUserId);
        assertEquals("janedoe", actualUserName);
        assertEquals(10.0f, actualGrade);
        assertTrue(actualStudentSubmissionInformation.isReviewable());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link StudentSubmissionInformation#StudentSubmissionInformation(String, String, String, float, boolean)}
     *   <li>{@link StudentSubmissionInformation#setDateSubmit(String)}
     *   <li>{@link StudentSubmissionInformation#setGrade(float)}
     *   <li>{@link StudentSubmissionInformation#setReviewable(boolean)}
     *   <li>{@link StudentSubmissionInformation#setUserId(String)}
     *   <li>{@link StudentSubmissionInformation#setUserName(String)}
     *   <li>{@link StudentSubmissionInformation#getDateSubmit()}
     *   <li>{@link StudentSubmissionInformation#getGrade()}
     *   <li>{@link StudentSubmissionInformation#getUserId()}
     *   <li>{@link StudentSubmissionInformation#getUserName()}
     *   <li>{@link StudentSubmissionInformation#isReviewable()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        StudentSubmissionInformation actualStudentSubmissionInformation = new StudentSubmissionInformation("42", "janedoe",
                "2020-03-01", 10.0f, true);
        actualStudentSubmissionInformation.setDateSubmit("2020-03-01");
        actualStudentSubmissionInformation.setGrade(10.0f);
        actualStudentSubmissionInformation.setReviewable(true);
        actualStudentSubmissionInformation.setUserId("42");
        actualStudentSubmissionInformation.setUserName("janedoe");
        String actualDateSubmit = actualStudentSubmissionInformation.getDateSubmit();
        float actualGrade = actualStudentSubmissionInformation.getGrade();
        String actualUserId = actualStudentSubmissionInformation.getUserId();
        String actualUserName = actualStudentSubmissionInformation.getUserName();

        // Assert that nothing has changed
        assertEquals("2020-03-01", actualDateSubmit);
        assertEquals("42", actualUserId);
        assertEquals("janedoe", actualUserName);
        assertEquals(10.0f, actualGrade);
        assertTrue(actualStudentSubmissionInformation.isReviewable());
    }
}
