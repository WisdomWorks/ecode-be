package com.example.codeE.model.topic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.codeE.model.course.Course;
import com.example.codeE.model.group.Group;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ViewPermissionTopicDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ViewPermissionTopic#ViewPermissionTopic()}
     *   <li>{@link ViewPermissionTopic#setGroup(Group)}
     *   <li>{@link ViewPermissionTopic#setGroupId(String)}
     *   <li>{@link ViewPermissionTopic#setTopic(Topic)}
     *   <li>{@link ViewPermissionTopic#setTopicId(String)}
     *   <li>{@link ViewPermissionTopic#getGroup()}
     *   <li>{@link ViewPermissionTopic#getGroupId()}
     *   <li>{@link ViewPermissionTopic#getTopic()}
     *   <li>{@link ViewPermissionTopic#getTopicId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        ViewPermissionTopic actualViewPermissionTopic = new ViewPermissionTopic();
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
        Group group = new Group();
        group.setCourse(course);
        group.setCourseId("42");
        group.setCreateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setGroupId("42");
        group.setGroupName("Group Name");
        group.setGroupStudents(new ArrayList<>());
        group.setUpdateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setViewPermissionMaterials(new ArrayList<>());
        group.setViewPermissionTopics(new ArrayList<>());
        actualViewPermissionTopic.setGroup(group);
        actualViewPermissionTopic.setGroupId("42");
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
        Topic topic = new Topic();
        topic.onUpdate();
        topic.setCourse(course2);
        topic.setCourseId("42");
        topic.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setDescription("The characteristics of someone or something");
        topic.setMaterials(new ArrayList<>());
        topic.setShowAll(true);
        topic.setTopicId("42");
        topic.setTopicName("Topic Name");
        topic.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setViewPermissionTopics(new ArrayList<>());
        actualViewPermissionTopic.setTopic(topic);
        actualViewPermissionTopic.setTopicId("42");
        Group actualGroup = actualViewPermissionTopic.getGroup();
        String actualGroupId = actualViewPermissionTopic.getGroupId();
        Topic actualTopic = actualViewPermissionTopic.getTopic();

        // Assert that nothing has changed
        assertEquals("42", actualGroupId);
        assertEquals("42", actualViewPermissionTopic.getTopicId());
        assertSame(group, actualGroup);
        assertSame(topic, actualTopic);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link ViewPermissionTopic#ViewPermissionTopic(String, String, Topic, Group)}
     *   <li>{@link ViewPermissionTopic#setGroup(Group)}
     *   <li>{@link ViewPermissionTopic#setGroupId(String)}
     *   <li>{@link ViewPermissionTopic#setTopic(Topic)}
     *   <li>{@link ViewPermissionTopic#setTopicId(String)}
     *   <li>{@link ViewPermissionTopic#getGroup()}
     *   <li>{@link ViewPermissionTopic#getGroupId()}
     *   <li>{@link ViewPermissionTopic#getTopic()}
     *   <li>{@link ViewPermissionTopic#getTopicId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
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
        topic.onUpdate();
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

        Group group = new Group();
        group.setCourse(course2);
        group.setCourseId("42");
        group.setCreateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setGroupId("42");
        group.setGroupName("Group Name");
        group.setGroupStudents(new ArrayList<>());
        group.setUpdateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setViewPermissionMaterials(new ArrayList<>());
        group.setViewPermissionTopics(new ArrayList<>());

        // Act
        ViewPermissionTopic actualViewPermissionTopic = new ViewPermissionTopic("42", "42", topic, group);
        Course course3 = new Course();
        course3.setCourseId("42");
        course3.setCourseName("Course Name");
        course3.setCourseStudents(new ArrayList<>());
        course3.setCourseTeachers(new ArrayList<>());
        course3.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course3.setDescription("The characteristics of someone or something");
        course3.setEnrollKey("Enroll Key");
        course3.setGroups(new ArrayList<>());
        course3.setSemester("Semester");
        course3.setTopics(new ArrayList<>());
        course3.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        Group group2 = new Group();
        group2.setCourse(course3);
        group2.setCourseId("42");
        group2.setCreateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group2.setGroupId("42");
        group2.setGroupName("Group Name");
        group2.setGroupStudents(new ArrayList<>());
        group2.setUpdateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group2.setViewPermissionMaterials(new ArrayList<>());
        group2.setViewPermissionTopics(new ArrayList<>());
        actualViewPermissionTopic.setGroup(group2);
        actualViewPermissionTopic.setGroupId("42");
        Course course4 = new Course();
        course4.setCourseId("42");
        course4.setCourseName("Course Name");
        course4.setCourseStudents(new ArrayList<>());
        course4.setCourseTeachers(new ArrayList<>());
        course4.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course4.setDescription("The characteristics of someone or something");
        course4.setEnrollKey("Enroll Key");
        course4.setGroups(new ArrayList<>());
        course4.setSemester("Semester");
        course4.setTopics(new ArrayList<>());
        course4.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        Topic topic2 = new Topic();
        topic2.onUpdate();
        topic2.setCourse(course4);
        topic2.setCourseId("42");
        topic2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic2.setDescription("The characteristics of someone or something");
        topic2.setMaterials(new ArrayList<>());
        topic2.setShowAll(true);
        topic2.setTopicId("42");
        topic2.setTopicName("Topic Name");
        topic2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic2.setViewPermissionTopics(new ArrayList<>());
        actualViewPermissionTopic.setTopic(topic2);
        actualViewPermissionTopic.setTopicId("42");
        Group actualGroup = actualViewPermissionTopic.getGroup();
        String actualGroupId = actualViewPermissionTopic.getGroupId();
        Topic actualTopic = actualViewPermissionTopic.getTopic();

        // Assert that nothing has changed
        assertEquals("42", actualGroupId);
        assertEquals("42", actualViewPermissionTopic.getTopicId());
        assertSame(group2, actualGroup);
        assertSame(topic2, actualTopic);
    }
}
