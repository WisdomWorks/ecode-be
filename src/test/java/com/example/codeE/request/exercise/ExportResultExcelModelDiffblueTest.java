package com.example.codeE.request.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ExportResultExcelModelDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ExportResultExcelModel#ExportResultExcelModel()}
     *   <li>{@link ExportResultExcelModel#setDate_Grade(String)}
     *   <li>{@link ExportResultExcelModel#setDate_Submit(String)}
     *   <li>{@link ExportResultExcelModel#setScore(String)}
     *   <li>{@link ExportResultExcelModel#setStudent_Name(String)}
     *   <li>{@link ExportResultExcelModel#setStudent_User_Name(String)}
     *   <li>{@link ExportResultExcelModel#getDate_Grade()}
     *   <li>{@link ExportResultExcelModel#getDate_Submit()}
     *   <li>{@link ExportResultExcelModel#getScore()}
     *   <li>{@link ExportResultExcelModel#getStudent_Name()}
     *   <li>{@link ExportResultExcelModel#getStudent_User_Name()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        ExportResultExcelModel actualExportResultExcelModel = new ExportResultExcelModel();
        actualExportResultExcelModel.setDate_Grade("2020-03-01");
        actualExportResultExcelModel.setDate_Submit("2020-03-01");
        actualExportResultExcelModel.setScore("Score");
        actualExportResultExcelModel.setStudent_Name("Student Name");
        actualExportResultExcelModel.setStudent_User_Name("Student User Name");
        String actualDate_Grade = actualExportResultExcelModel.getDate_Grade();
        String actualDate_Submit = actualExportResultExcelModel.getDate_Submit();
        String actualScore = actualExportResultExcelModel.getScore();
        String actualStudent_Name = actualExportResultExcelModel.getStudent_Name();

        // Assert that nothing has changed
        assertEquals("2020-03-01", actualDate_Grade);
        assertEquals("2020-03-01", actualDate_Submit);
        assertEquals("Score", actualScore);
        assertEquals("Student Name", actualStudent_Name);
        assertEquals("Student User Name", actualExportResultExcelModel.getStudent_User_Name());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link ExportResultExcelModel#ExportResultExcelModel(String, String, String, String, String)}
     *   <li>{@link ExportResultExcelModel#setDate_Grade(String)}
     *   <li>{@link ExportResultExcelModel#setDate_Submit(String)}
     *   <li>{@link ExportResultExcelModel#setScore(String)}
     *   <li>{@link ExportResultExcelModel#setStudent_Name(String)}
     *   <li>{@link ExportResultExcelModel#setStudent_User_Name(String)}
     *   <li>{@link ExportResultExcelModel#getDate_Grade()}
     *   <li>{@link ExportResultExcelModel#getDate_Submit()}
     *   <li>{@link ExportResultExcelModel#getScore()}
     *   <li>{@link ExportResultExcelModel#getStudent_Name()}
     *   <li>{@link ExportResultExcelModel#getStudent_User_Name()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        ExportResultExcelModel actualExportResultExcelModel = new ExportResultExcelModel("Student Name",
                "Student User Name", "2020-03-01", "2020-03-01", "Score");
        actualExportResultExcelModel.setDate_Grade("2020-03-01");
        actualExportResultExcelModel.setDate_Submit("2020-03-01");
        actualExportResultExcelModel.setScore("Score");
        actualExportResultExcelModel.setStudent_Name("Student Name");
        actualExportResultExcelModel.setStudent_User_Name("Student User Name");
        String actualDate_Grade = actualExportResultExcelModel.getDate_Grade();
        String actualDate_Submit = actualExportResultExcelModel.getDate_Submit();
        String actualScore = actualExportResultExcelModel.getScore();
        String actualStudent_Name = actualExportResultExcelModel.getStudent_Name();

        // Assert that nothing has changed
        assertEquals("2020-03-01", actualDate_Grade);
        assertEquals("2020-03-01", actualDate_Submit);
        assertEquals("Score", actualScore);
        assertEquals("Student Name", actualStudent_Name);
        assertEquals("Student User Name", actualExportResultExcelModel.getStudent_User_Name());
    }
}
