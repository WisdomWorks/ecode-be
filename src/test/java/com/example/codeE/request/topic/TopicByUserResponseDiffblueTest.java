package com.example.codeE.request.topic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.codeE.model.course.Course;
import com.example.codeE.model.course.CourseStudent;
import com.example.codeE.model.material.Material;
import com.example.codeE.model.topic.Topic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TopicByUserResponseDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link TopicByUserResponse#TopicByUserResponse()}
     *   <li>{@link TopicByUserResponse#setMaterials(List)}
     *   <li>{@link TopicByUserResponse#setTopic(Topic)}
     *   <li>{@link TopicByUserResponse#getMaterials()}
     *   <li>{@link TopicByUserResponse#getTopic()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        TopicByUserResponse actualTopicByUserResponse = new TopicByUserResponse();
        ArrayList<Material> materials = new ArrayList<>();
        actualTopicByUserResponse.setMaterials(materials);
        Course course = new Course();
        course.setCourseId("42");
        course.setCourseName("Course Name");
        course.setCourseStudents(new ArrayList<>());
        course.setCourseTeachers(new ArrayList<>());
        course.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course.setDescription("The characteristics of someone or something");
        course.setEnrollKey("Enroll Key");
        course.setGroups(new ArrayList<>());
        course.setSemester("Semester");
        course.setTopics(new ArrayList<>());
        course.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        Topic topic = new Topic();
        topic.setCourse(course);
        topic.setCourseId("42");
        topic.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setDescription("The characteristics of someone or something");
        topic.setMaterials(new ArrayList<>());
        topic.setShowAll(true);
        topic.setTopicId("42");
        topic.setTopicName("Topic Name");
        topic.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setViewPermissionTopics(new ArrayList<>());
        actualTopicByUserResponse.setTopic(topic);
        List<Material> actualMaterials = actualTopicByUserResponse.getMaterials();

        // Assert that nothing has changed
        assertSame(topic, actualTopicByUserResponse.getTopic());
        assertSame(materials, actualMaterials);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link TopicByUserResponse#TopicByUserResponse(Topic, List)}
     *   <li>{@link TopicByUserResponse#setMaterials(List)}
     *   <li>{@link TopicByUserResponse#setTopic(Topic)}
     *   <li>{@link TopicByUserResponse#getMaterials()}
     *   <li>{@link TopicByUserResponse#getTopic()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        Course course = new Course();
        course.setCourseId("42");
        course.setCourseName("Course Name");
        ArrayList<CourseStudent> courseStudents = new ArrayList<>();
        course.setCourseStudents(courseStudents);
        course.setCourseTeachers(new ArrayList<>());
        course.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course.setDescription("The characteristics of someone or something");
        course.setEnrollKey("Enroll Key");
        course.setGroups(new ArrayList<>());
        course.setSemester("Semester");
        course.setTopics(new ArrayList<>());
        course.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        Topic topic = new Topic();
        topic.setCourse(course);
        topic.setCourseId("42");
        topic.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setDescription("The characteristics of someone or something");
        topic.setMaterials(new ArrayList<>());
        topic.setShowAll(true);
        topic.setTopicId("42");
        topic.setTopicName("Topic Name");
        topic.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setViewPermissionTopics(new ArrayList<>());

        // Act
        TopicByUserResponse actualTopicByUserResponse = new TopicByUserResponse(topic, new ArrayList<>());
        ArrayList<Material> materials = new ArrayList<>();
        actualTopicByUserResponse.setMaterials(materials);
        Course course2 = new Course();
        course2.setCourseId("42");
        course2.setCourseName("Course Name");
        course2.setCourseStudents(new ArrayList<>());
        course2.setCourseTeachers(new ArrayList<>());
        course2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course2.setDescription("The characteristics of someone or something");
        course2.setEnrollKey("Enroll Key");
        course2.setGroups(new ArrayList<>());
        course2.setSemester("Semester");
        course2.setTopics(new ArrayList<>());
        course2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        Topic topic2 = new Topic();
        topic2.setCourse(course2);
        topic2.setCourseId("42");
        topic2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic2.setDescription("The characteristics of someone or something");
        topic2.setMaterials(new ArrayList<>());
        topic2.setShowAll(true);
        topic2.setTopicId("42");
        topic2.setTopicName("Topic Name");
        topic2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic2.setViewPermissionTopics(new ArrayList<>());
        actualTopicByUserResponse.setTopic(topic2);
        List<Material> actualMaterials = actualTopicByUserResponse.getMaterials();

        // Assert that nothing has changed
        assertEquals(courseStudents, actualMaterials);
        assertSame(topic2, actualTopicByUserResponse.getTopic());
        assertSame(materials, actualMaterials);
    }
}
