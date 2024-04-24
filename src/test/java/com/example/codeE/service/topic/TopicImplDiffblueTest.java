package com.example.codeE.service.topic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.model.course.Course;
import com.example.codeE.model.group.Group;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.model.topic.ViewPermissionTopic;
import com.example.codeE.repository.CourseRepository;
import com.example.codeE.repository.GroupRepository;
import com.example.codeE.repository.TopicRepository;
import com.example.codeE.repository.ViewPermissionTopicRepository;
import com.example.codeE.request.topic.CreateTopicRequest;
import com.example.codeE.request.topic.TopicGetResponse;
import com.example.codeE.request.topic.UpdateTopicRequest;
import com.example.codeE.service.exercise.EssayExerciseService;
import com.example.codeE.service.exercise.ExerciseService;
import com.example.codeE.service.exercise.QuizExerciseService;
import com.example.codeE.service.material.MaterialService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TopicImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class TopicImplDiffblueTest {
    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private EssayExerciseService essayExerciseService;

    @MockBean
    private ExerciseService exerciseService;

    @MockBean
    private GroupRepository groupRepository;

    @MockBean
    private MaterialService materialService;

    @MockBean
    private QuizExerciseService quizExerciseService;

    @Autowired
    private TopicImpl topicImpl;

    @MockBean
    private TopicRepository topicRepository;

    @MockBean
    private ViewPermissionTopicRepository viewPermissionTopicRepository;

    /**
     * Method under test: {@link TopicImpl#getAllTopicsByCourseId(String)}
     */
    @Test
    void testGetAllTopicsByCourseId() {
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
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(topicRepository.getAllTopicsByCourseId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<TopicGetResponse> actualAllTopicsByCourseId = topicImpl.getAllTopicsByCourseId("42");

        // Assert
        verify(topicRepository).getAllTopicsByCourseId(eq("42"));
        verify(courseRepository).findById(eq("42"));
        assertTrue(actualAllTopicsByCourseId.isEmpty());
    }

    /**
     * Method under test: {@link TopicImpl#getAllTopicsByCourseId(String)}
     */
    @Test
    void testGetAllTopicsByCourseId2() {
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
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(topicRepository.getAllTopicsByCourseId(Mockito.<String>any())).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> topicImpl.getAllTopicsByCourseId("42"));
        verify(topicRepository).getAllTopicsByCourseId(eq("42"));
        verify(courseRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link TopicImpl#getAllTopicsByCourseId(String)}
     */
    @Test
    void testGetAllTopicsByCourseId3() {
        // Arrange
        Optional<Course> emptyResult = Optional.empty();
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> topicImpl.getAllTopicsByCourseId("42"));
        verify(courseRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link TopicImpl#getAllTopicsByCourseId(String)}
     */
    @Test
    void testGetAllTopicsByCourseId4() {
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
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(exerciseService.getExercisesByTopicId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(materialService.getAllByTopicId(Mockito.<String>any())).thenReturn(new ArrayList<>());

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

        ArrayList<Topic> topicList = new ArrayList<>();
        topicList.add(topic);
        when(topicRepository.getAllTopicsByCourseId(Mockito.<String>any())).thenReturn(topicList);
        when(viewPermissionTopicRepository.getAllGroupsByTopicId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<TopicGetResponse> actualAllTopicsByCourseId = topicImpl.getAllTopicsByCourseId("42");

        // Assert
        verify(topicRepository).getAllTopicsByCourseId(eq("42"));
        verify(viewPermissionTopicRepository).getAllGroupsByTopicId(eq("42"));
        verify(exerciseService).getExercisesByTopicId(eq("42"));
        verify(materialService).getAllByTopicId(eq("42"));
        verify(courseRepository).findById(eq("42"));
        assertEquals(1, actualAllTopicsByCourseId.size());
    }

    /**
     * Method under test: {@link TopicImpl#getAllTopicsByCourseId(String)}
     */
    @Test
    void testGetAllTopicsByCourseId5() {
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
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(exerciseService.getExercisesByTopicId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(materialService.getAllByTopicId(Mockito.<String>any())).thenReturn(new ArrayList<>());

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

        ArrayList<Topic> topicList = new ArrayList<>();
        topicList.add(topic);
        when(topicRepository.getAllTopicsByCourseId(Mockito.<String>any())).thenReturn(topicList);
        when(viewPermissionTopicRepository.getAllGroupsByTopicId(Mockito.<String>any()))
                .thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> topicImpl.getAllTopicsByCourseId("42"));
        verify(topicRepository).getAllTopicsByCourseId(eq("42"));
        verify(viewPermissionTopicRepository).getAllGroupsByTopicId(eq("42"));
        verify(exerciseService).getExercisesByTopicId(eq("42"));
        verify(materialService).getAllByTopicId(eq("42"));
        verify(courseRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link TopicImpl#getAllTopicsByCourseId(String)}
     */
    @Test
    void testGetAllTopicsByCourseId6() {
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
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(exerciseService.getExercisesByTopicId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(materialService.getAllByTopicId(Mockito.<String>any())).thenReturn(new ArrayList<>());

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

        Course course3 = new Course();
        course3.setCourseId("Course Id");
        course3.setCourseName("42");
        course3.setCourseStudents(new ArrayList<>());
        course3.setCourseTeachers(new ArrayList<>());
        course3.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course3.setDescription("Description");
        course3.setEnrollKey("42");
        course3.setGroups(new ArrayList<>());
        course3.setSemester("42");
        course3.setTopics(new ArrayList<>());
        course3.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        Topic topic2 = new Topic();
        topic2.setCourse(course3);
        topic2.setCourseId("Course Id");
        topic2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic2.setDescription("Description");
        topic2.setMaterials(new ArrayList<>());
        topic2.setShowAll(false);
        topic2.setTopicId("Topic Id");
        topic2.setTopicName("42");
        topic2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic2.setViewPermissionTopics(new ArrayList<>());

        ArrayList<Topic> topicList = new ArrayList<>();
        topicList.add(topic2);
        topicList.add(topic);
        when(topicRepository.getAllTopicsByCourseId(Mockito.<String>any())).thenReturn(topicList);
        when(viewPermissionTopicRepository.getAllGroupsByTopicId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<TopicGetResponse> actualAllTopicsByCourseId = topicImpl.getAllTopicsByCourseId("42");

        // Assert
        verify(topicRepository).getAllTopicsByCourseId(eq("42"));
        verify(viewPermissionTopicRepository, atLeast(1)).getAllGroupsByTopicId(Mockito.<String>any());
        verify(exerciseService, atLeast(1)).getExercisesByTopicId(Mockito.<String>any());
        verify(materialService, atLeast(1)).getAllByTopicId(Mockito.<String>any());
        verify(courseRepository).findById(eq("42"));
        assertEquals(2, actualAllTopicsByCourseId.size());
    }

    /**
     * Method under test: {@link TopicImpl#getAllTopicsByCourseId(String)}
     */
    @Test
    void testGetAllTopicsByCourseId7() {
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
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(exerciseService.getExercisesByTopicId(Mockito.<String>any())).thenReturn(new ArrayList<>());

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
        Optional<Group> ofResult2 = Optional.of(group);
        when(groupRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        when(materialService.getAllByTopicId(Mockito.<String>any())).thenReturn(new ArrayList<>());

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

        Topic topic = new Topic();
        topic.setCourse(course3);
        topic.setCourseId("42");
        topic.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setDescription("The characteristics of someone or something");
        topic.setMaterials(new ArrayList<>());
        topic.setShowAll(true);
        topic.setTopicId("42");
        topic.setTopicName("Topic Name");
        topic.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setViewPermissionTopics(new ArrayList<>());

        ArrayList<Topic> topicList = new ArrayList<>();
        topicList.add(topic);
        when(topicRepository.getAllTopicsByCourseId(Mockito.<String>any())).thenReturn(topicList);

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

        Group group2 = new Group();
        group2.setCourse(course4);
        group2.setCourseId("42");
        group2.setCreateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group2.setGroupId("42");
        group2.setGroupName("Group Name");
        group2.setGroupStudents(new ArrayList<>());
        group2.setUpdateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group2.setViewPermissionMaterials(new ArrayList<>());
        group2.setViewPermissionTopics(new ArrayList<>());

        Course course5 = new Course();
        course5.setCourseId("42");
        course5.setCourseName("Course Name");
        course5.setCourseStudents(new ArrayList<>());
        course5.setCourseTeachers(new ArrayList<>());
        course5.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course5.setDescription("The characteristics of someone or something");
        course5.setEnrollKey("Enroll Key");
        course5.setGroups(new ArrayList<>());
        course5.setSemester("Semester");
        course5.setTopics(new ArrayList<>());
        course5.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        Topic topic2 = new Topic();
        topic2.setCourse(course5);
        topic2.setCourseId("42");
        topic2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic2.setDescription("The characteristics of someone or something");
        topic2.setMaterials(new ArrayList<>());
        topic2.setShowAll(true);
        topic2.setTopicId("42");
        topic2.setTopicName("Topic Name");
        topic2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic2.setViewPermissionTopics(new ArrayList<>());

        ViewPermissionTopic viewPermissionTopic = new ViewPermissionTopic();
        viewPermissionTopic.setGroup(group2);
        viewPermissionTopic.setGroupId("42");
        viewPermissionTopic.setTopic(topic2);
        viewPermissionTopic.setTopicId("42");

        ArrayList<ViewPermissionTopic> viewPermissionTopicList = new ArrayList<>();
        viewPermissionTopicList.add(viewPermissionTopic);
        when(viewPermissionTopicRepository.getAllGroupsByTopicId(Mockito.<String>any()))
                .thenReturn(viewPermissionTopicList);

        // Act
        List<TopicGetResponse> actualAllTopicsByCourseId = topicImpl.getAllTopicsByCourseId("42");

        // Assert
        verify(topicRepository).getAllTopicsByCourseId(eq("42"));
        verify(viewPermissionTopicRepository).getAllGroupsByTopicId(eq("42"));
        verify(exerciseService).getExercisesByTopicId(eq("42"));
        verify(materialService).getAllByTopicId(eq("42"));
        verify(courseRepository).findById(eq("42"));
        verify(groupRepository).findById(eq("42"));
        assertEquals(1, actualAllTopicsByCourseId.size());
    }

    /**
     * Method under test: {@link TopicImpl#updateTopic(UpdateTopicRequest)}
     */
    @Test
    void testUpdateTopic() {
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
        Optional<Topic> ofResult = Optional.of(topic);

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
        when(topicRepository.save(Mockito.<Topic>any())).thenReturn(topic2);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Topic actualUpdateTopicResult = topicImpl
                .updateTopic(new UpdateTopicRequest("42", "Topic Name", "The characteristics of someone or something", true));

        // Assert
        verify(topicRepository, atLeast(1)).findById(eq("42"));
        verify(topicRepository).save(isA(Topic.class));
        LocalTime expectedToLocalTimeResult = actualUpdateTopicResult.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualUpdateTopicResult.getCreatedDate().toLocalTime());
    }

    /**
     * Method under test: {@link TopicImpl#updateTopic(UpdateTopicRequest)}
     */
    @Test
    void testUpdateTopic2() {
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
        Optional<Topic> ofResult = Optional.of(topic);
        when(topicRepository.save(Mockito.<Topic>any())).thenThrow(new RuntimeException("foo"));
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> topicImpl
                .updateTopic(new UpdateTopicRequest("42", "Topic Name", "The characteristics of someone or something", true)));
        verify(topicRepository, atLeast(1)).findById(eq("42"));
        verify(topicRepository).save(isA(Topic.class));
    }

    /**
     * Method under test: {@link TopicImpl#updateTopic(UpdateTopicRequest)}
     */
    @Test
    void testUpdateTopic3() {
        // Arrange
        Optional<Topic> emptyResult = Optional.empty();
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> topicImpl
                .updateTopic(new UpdateTopicRequest("42", "Topic Name", "The characteristics of someone or something", true)));
        verify(topicRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link TopicImpl#getAllGroupsByTopicId(String)}
     */
    @Test
    void testGetAllGroupsByTopicId() {
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
        Optional<Topic> ofResult = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(viewPermissionTopicRepository.getAllGroupsByTopicId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<Group> actualAllGroupsByTopicId = topicImpl.getAllGroupsByTopicId("42");

        // Assert
        verify(viewPermissionTopicRepository).getAllGroupsByTopicId(eq("42"));
        verify(topicRepository).findById(eq("42"));
        assertTrue(actualAllGroupsByTopicId.isEmpty());
    }

    /**
     * Method under test: {@link TopicImpl#getAllGroupsByTopicId(String)}
     */
    @Test
    void testGetAllGroupsByTopicId2() {
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
        Optional<Topic> ofResult = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(viewPermissionTopicRepository.getAllGroupsByTopicId(Mockito.<String>any()))
                .thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> topicImpl.getAllGroupsByTopicId("42"));
        verify(viewPermissionTopicRepository).getAllGroupsByTopicId(eq("42"));
        verify(topicRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link TopicImpl#getAllGroupsByTopicId(String)}
     */
    @Test
    void testGetAllGroupsByTopicId3() {
        // Arrange
        Optional<Topic> emptyResult = Optional.empty();
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> topicImpl.getAllGroupsByTopicId("42"));
        verify(topicRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link TopicImpl#getAllGroupsByTopicId(String)}
     */
    @Test
    void testGetAllGroupsByTopicId4() {
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
        Optional<Group> ofResult = Optional.of(group);
        when(groupRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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
        Optional<Topic> ofResult2 = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

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

        ViewPermissionTopic viewPermissionTopic = new ViewPermissionTopic();
        viewPermissionTopic.setGroup(group2);
        viewPermissionTopic.setGroupId("42");
        viewPermissionTopic.setTopic(topic2);
        viewPermissionTopic.setTopicId("42");

        ArrayList<ViewPermissionTopic> viewPermissionTopicList = new ArrayList<>();
        viewPermissionTopicList.add(viewPermissionTopic);
        when(viewPermissionTopicRepository.getAllGroupsByTopicId(Mockito.<String>any()))
                .thenReturn(viewPermissionTopicList);

        // Act
        List<Group> actualAllGroupsByTopicId = topicImpl.getAllGroupsByTopicId("42");

        // Assert
        verify(viewPermissionTopicRepository).getAllGroupsByTopicId(eq("42"));
        verify(groupRepository).findById(eq("42"));
        verify(topicRepository).findById(eq("42"));
        assertEquals(1, actualAllGroupsByTopicId.size());
    }

    /**
     * Method under test: {@link TopicImpl#removeViewPermission(String, List)}
     */
    @Test
    void testRemoveViewPermission() {
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
        Optional<Topic> ofResult = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        boolean actualRemoveViewPermissionResult = topicImpl.removeViewPermission("42", new ArrayList<>());

        // Assert
        verify(topicRepository).findById(eq("42"));
        assertTrue(actualRemoveViewPermissionResult);
    }

    /**
     * Method under test: {@link TopicImpl#removeViewPermission(String, List)}
     */
    @Test
    void testRemoveViewPermission2() {
        // Arrange
        Optional<Topic> emptyResult = Optional.empty();
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> topicImpl.removeViewPermission("42", new ArrayList<>()));
        verify(topicRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link TopicImpl#removeViewPermission(String, List)}
     */
    @Test
    void testRemoveViewPermission3() {
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
        Optional<Group> ofResult = Optional.of(group);
        when(groupRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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
        Optional<Topic> ofResult2 = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        doNothing().when(viewPermissionTopicRepository).removeViewPermission(Mockito.<String>any(), Mockito.<String>any());

        ArrayList<String> groupIds = new ArrayList<>();
        groupIds.add("foo");

        // Act
        boolean actualRemoveViewPermissionResult = topicImpl.removeViewPermission("42", groupIds);

        // Assert
        verify(viewPermissionTopicRepository).removeViewPermission(eq("42"), eq("foo"));
        verify(topicRepository).findById(eq("42"));
        verify(groupRepository).findById(eq("foo"));
        assertTrue(actualRemoveViewPermissionResult);
    }

    /**
     * Method under test: {@link TopicImpl#removeViewPermission(String, List)}
     */
    @Test
    void testRemoveViewPermission4() {
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
        Optional<Group> ofResult = Optional.of(group);
        when(groupRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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
        Optional<Topic> ofResult2 = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        doThrow(new RuntimeException("foo")).when(viewPermissionTopicRepository)
                .removeViewPermission(Mockito.<String>any(), Mockito.<String>any());

        ArrayList<String> groupIds = new ArrayList<>();
        groupIds.add("foo");

        // Act and Assert
        assertThrows(RuntimeException.class, () -> topicImpl.removeViewPermission("42", groupIds));
        verify(viewPermissionTopicRepository).removeViewPermission(eq("42"), eq("foo"));
        verify(topicRepository).findById(eq("42"));
        verify(groupRepository).findById(eq("foo"));
    }

    /**
     * Method under test: {@link TopicImpl#removeViewPermission(String, List)}
     */
    @Test
    void testRemoveViewPermission5() {
        // Arrange
        Optional<Group> emptyResult = Optional.empty();
        when(groupRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

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
        Optional<Topic> ofResult = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        ArrayList<String> groupIds = new ArrayList<>();
        groupIds.add("foo");

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> topicImpl.removeViewPermission("42", groupIds));
        verify(topicRepository).findById(eq("42"));
        verify(groupRepository).findById(eq("foo"));
    }

    /**
     * Method under test: {@link TopicImpl#addViewPermission(String, List, boolean)}
     */
    @Test
    void testAddViewPermission() {
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
        Optional<Topic> ofResult = Optional.of(topic);

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
        when(topicRepository.save(Mockito.<Topic>any())).thenReturn(topic2);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        doNothing().when(viewPermissionTopicRepository).removeViewPermissionByTopicId(Mockito.<String>any());

        // Act
        boolean actualAddViewPermissionResult = topicImpl.addViewPermission("42", new ArrayList<>(), true);

        // Assert
        verify(viewPermissionTopicRepository).removeViewPermissionByTopicId(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(topicRepository).save(isA(Topic.class));
        assertTrue(actualAddViewPermissionResult);
    }

    /**
     * Method under test: {@link TopicImpl#addViewPermission(String, List, boolean)}
     */
    @Test
    void testAddViewPermission2() {
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
        Optional<Topic> ofResult = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        doThrow(new RuntimeException("foo")).when(viewPermissionTopicRepository)
                .removeViewPermissionByTopicId(Mockito.<String>any());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> topicImpl.addViewPermission("42", new ArrayList<>(), true));
        verify(viewPermissionTopicRepository).removeViewPermissionByTopicId(eq("42"));
        verify(topicRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link TopicImpl#addViewPermission(String, List, boolean)}
     */
    @Test
    void testAddViewPermission3() {
        // Arrange
        Optional<Topic> emptyResult = Optional.empty();
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> topicImpl.addViewPermission("42", new ArrayList<>(), true));
        verify(topicRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link TopicImpl#addViewPermission(String, List, boolean)}
     */
    @Test
    void testAddViewPermission4() {
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
        Optional<Group> ofResult = Optional.of(group);
        when(groupRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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
        Optional<Topic> ofResult2 = Optional.of(topic);

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

        Topic topic2 = new Topic();
        topic2.setCourse(course3);
        topic2.setCourseId("42");
        topic2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic2.setDescription("The characteristics of someone or something");
        topic2.setMaterials(new ArrayList<>());
        topic2.setShowAll(true);
        topic2.setTopicId("42");
        topic2.setTopicName("Topic Name");
        topic2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic2.setViewPermissionTopics(new ArrayList<>());
        when(topicRepository.save(Mockito.<Topic>any())).thenReturn(topic2);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        doNothing().when(viewPermissionTopicRepository).addViewPermission(Mockito.<String>any(), Mockito.<String>any());
        doNothing().when(viewPermissionTopicRepository).removeViewPermissionByTopicId(Mockito.<String>any());

        ArrayList<String> groupIds = new ArrayList<>();
        groupIds.add("foo");

        // Act
        boolean actualAddViewPermissionResult = topicImpl.addViewPermission("42", groupIds, true);

        // Assert
        verify(viewPermissionTopicRepository).addViewPermission(eq("42"), eq("foo"));
        verify(viewPermissionTopicRepository).removeViewPermissionByTopicId(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(groupRepository).findById(eq("foo"));
        verify(topicRepository).save(isA(Topic.class));
        assertTrue(actualAddViewPermissionResult);
    }

    /**
     * Method under test: {@link TopicImpl#addViewPermission(String, List, boolean)}
     */
    @Test
    void testAddViewPermission5() {
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
        Optional<Group> ofResult = Optional.of(group);
        when(groupRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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
        Optional<Topic> ofResult2 = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        doThrow(new RuntimeException("foo")).when(viewPermissionTopicRepository)
                .addViewPermission(Mockito.<String>any(), Mockito.<String>any());
        doNothing().when(viewPermissionTopicRepository).removeViewPermissionByTopicId(Mockito.<String>any());

        ArrayList<String> groupIds = new ArrayList<>();
        groupIds.add("foo");

        // Act and Assert
        assertThrows(RuntimeException.class, () -> topicImpl.addViewPermission("42", groupIds, true));
        verify(viewPermissionTopicRepository).addViewPermission(eq("42"), eq("foo"));
        verify(viewPermissionTopicRepository).removeViewPermissionByTopicId(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(groupRepository).findById(eq("foo"));
    }

    /**
     * Method under test: {@link TopicImpl#getTopicByUserId(String, String)}
     */
    @Test
    void testGetTopicByUserId() {
        // Arrange
        when(topicRepository.getTopicByUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<TopicGetResponse> actualTopicByUserId = topicImpl.getTopicByUserId("42", "42");

        // Assert
        verify(topicRepository).getTopicByUser(eq("42"), eq("42"));
        assertTrue(actualTopicByUserId.isEmpty());
    }

    /**
     * Method under test: {@link TopicImpl#getTopicByUserId(String, String)}
     */
    @Test
    void testGetTopicByUserId2() {
        // Arrange
        when(exerciseService.getExercisesByUserId(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(new ArrayList<>());
        when(materialService.getMaterialByUserId(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(new ArrayList<>());

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

        ArrayList<Topic> topicList = new ArrayList<>();
        topicList.add(topic);
        when(topicRepository.getTopicByUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn(topicList);
        when(viewPermissionTopicRepository.getAllGroupsByTopicId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<TopicGetResponse> actualTopicByUserId = topicImpl.getTopicByUserId("42", "42");

        // Assert
        verify(topicRepository).getTopicByUser(eq("42"), eq("42"));
        verify(viewPermissionTopicRepository).getAllGroupsByTopicId(eq("42"));
        verify(exerciseService).getExercisesByUserId(eq("42"), eq("42"));
        verify(materialService).getMaterialByUserId(eq("42"), eq("42"));
        assertEquals(1, actualTopicByUserId.size());
    }

    /**
     * Method under test: {@link TopicImpl#getTopicByUserId(String, String)}
     */
    @Test
    void testGetTopicByUserId3() {
        // Arrange
        when(exerciseService.getExercisesByUserId(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(new ArrayList<>());
        when(materialService.getMaterialByUserId(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(new ArrayList<>());

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

        ArrayList<Topic> topicList = new ArrayList<>();
        topicList.add(topic);
        when(topicRepository.getTopicByUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn(topicList);
        when(viewPermissionTopicRepository.getAllGroupsByTopicId(Mockito.<String>any()))
                .thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> topicImpl.getTopicByUserId("42", "42"));
        verify(topicRepository).getTopicByUser(eq("42"), eq("42"));
        verify(viewPermissionTopicRepository).getAllGroupsByTopicId(eq("42"));
        verify(exerciseService).getExercisesByUserId(eq("42"), eq("42"));
        verify(materialService).getMaterialByUserId(eq("42"), eq("42"));
    }

    /**
     * Method under test: {@link TopicImpl#getTopicByUserId(String, String)}
     */
    @Test
    void testGetTopicByUserId4() {
        // Arrange
        when(exerciseService.getExercisesByUserId(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(new ArrayList<>());
        when(materialService.getMaterialByUserId(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(new ArrayList<>());

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

        Course course2 = new Course();
        course2.setCourseId("Course Id");
        course2.setCourseName("42");
        course2.setCourseStudents(new ArrayList<>());
        course2.setCourseTeachers(new ArrayList<>());
        course2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course2.setDescription("Description");
        course2.setEnrollKey("42");
        course2.setGroups(new ArrayList<>());
        course2.setSemester("42");
        course2.setTopics(new ArrayList<>());
        course2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        Topic topic2 = new Topic();
        topic2.setCourse(course2);
        topic2.setCourseId("Course Id");
        topic2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic2.setDescription("Description");
        topic2.setMaterials(new ArrayList<>());
        topic2.setShowAll(false);
        topic2.setTopicId("Topic Id");
        topic2.setTopicName("42");
        topic2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic2.setViewPermissionTopics(new ArrayList<>());

        ArrayList<Topic> topicList = new ArrayList<>();
        topicList.add(topic2);
        topicList.add(topic);
        when(topicRepository.getTopicByUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn(topicList);
        when(viewPermissionTopicRepository.getAllGroupsByTopicId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<TopicGetResponse> actualTopicByUserId = topicImpl.getTopicByUserId("42", "42");

        // Assert
        verify(topicRepository).getTopicByUser(eq("42"), eq("42"));
        verify(viewPermissionTopicRepository, atLeast(1)).getAllGroupsByTopicId(Mockito.<String>any());
        verify(exerciseService, atLeast(1)).getExercisesByUserId(Mockito.<String>any(), eq("42"));
        verify(materialService, atLeast(1)).getMaterialByUserId(eq("42"), Mockito.<String>any());
        assertEquals(2, actualTopicByUserId.size());
    }

    /**
     * Method under test: {@link TopicImpl#getTopicByUserId(String, String)}
     */
    @Test
    void testGetTopicByUserId5() {
        // Arrange
        when(exerciseService.getExercisesByUserId(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(new ArrayList<>());

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
        Optional<Group> ofResult = Optional.of(group);
        when(groupRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(materialService.getMaterialByUserId(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(new ArrayList<>());

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

        ArrayList<Topic> topicList = new ArrayList<>();
        topicList.add(topic);
        when(topicRepository.getTopicByUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn(topicList);

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

        ViewPermissionTopic viewPermissionTopic = new ViewPermissionTopic();
        viewPermissionTopic.setGroup(group2);
        viewPermissionTopic.setGroupId("42");
        viewPermissionTopic.setTopic(topic2);
        viewPermissionTopic.setTopicId("42");

        ArrayList<ViewPermissionTopic> viewPermissionTopicList = new ArrayList<>();
        viewPermissionTopicList.add(viewPermissionTopic);
        when(viewPermissionTopicRepository.getAllGroupsByTopicId(Mockito.<String>any()))
                .thenReturn(viewPermissionTopicList);

        // Act
        List<TopicGetResponse> actualTopicByUserId = topicImpl.getTopicByUserId("42", "42");

        // Assert
        verify(topicRepository).getTopicByUser(eq("42"), eq("42"));
        verify(viewPermissionTopicRepository).getAllGroupsByTopicId(eq("42"));
        verify(exerciseService).getExercisesByUserId(eq("42"), eq("42"));
        verify(materialService).getMaterialByUserId(eq("42"), eq("42"));
        verify(groupRepository).findById(eq("42"));
        assertEquals(1, actualTopicByUserId.size());
    }

    /**
     * Method under test: {@link TopicImpl#createOne(CreateTopicRequest)}
     */
    @Test
    void testCreateOne() {
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
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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
        when(topicRepository.save(Mockito.<Topic>any())).thenReturn(topic);

        // Act
        Topic actualCreateOneResult = topicImpl
                .createOne(new CreateTopicRequest("42", "Topic Name", "The characteristics of someone or something"));

        // Assert
        verify(courseRepository).findById(eq("42"));
        verify(topicRepository).save(isA(Topic.class));
        LocalTime expectedToLocalTimeResult = actualCreateOneResult.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualCreateOneResult.getCreatedDate().toLocalTime());
    }

    /**
     * Method under test: {@link TopicImpl#createOne(CreateTopicRequest)}
     */
    @Test
    void testCreateOne2() {
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
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(topicRepository.save(Mockito.<Topic>any())).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> topicImpl
                .createOne(new CreateTopicRequest("42", "Topic Name", "The characteristics of someone or something")));
        verify(courseRepository).findById(eq("42"));
        verify(topicRepository).save(isA(Topic.class));
    }

    /**
     * Method under test: {@link TopicImpl#createOne(CreateTopicRequest)}
     */
    @Test
    void testCreateOne3() {
        // Arrange
        Optional<Course> emptyResult = Optional.empty();
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> topicImpl
                .createOne(new CreateTopicRequest("42", "Topic Name", "The characteristics of someone or something")));
        verify(courseRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link TopicImpl#getById(String)}
     */
    @Test
    void testGetById() {
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
        Optional<Topic> ofResult = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Topic actualById = topicImpl.getById("42");

        // Assert
        verify(topicRepository).findById(eq("42"));
        LocalTime expectedToLocalTimeResult = actualById.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualById.getCreatedDate().toLocalTime());
    }

    /**
     * Method under test: {@link TopicImpl#getById(String)}
     */
    @Test
    void testGetById2() {
        // Arrange
        Optional<Topic> emptyResult = Optional.empty();
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> topicImpl.getById("42"));
        verify(topicRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link TopicImpl#getById(String)}
     */
    @Test
    void testGetById3() {
        // Arrange
        when(topicRepository.findById(Mockito.<String>any())).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> topicImpl.getById("42"));
        verify(topicRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link TopicImpl#getAll()}
     */
    @Test
    void testGetAll() {
        // Arrange, Act and Assert
        assertThrows(UnsupportedOperationException.class, () -> topicImpl.getAll());
    }

    /**
     * Method under test: {@link TopicImpl#deleteById(String)}
     */
    @Test
    void testDeleteById() {
        // Arrange
        when(exerciseService.getExercisesByTopicId(Mockito.<String>any())).thenReturn(new ArrayList<>());

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
        Optional<Topic> ofResult = Optional.of(topic);
        doNothing().when(topicRepository).deleteById(Mockito.<String>any());
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        topicImpl.deleteById("42");

        // Assert that nothing has changed
        verify(exerciseService).getExercisesByTopicId(eq("42"));
        verify(topicRepository).deleteById(eq("42"));
        verify(topicRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link TopicImpl#deleteById(String)}
     */
    @Test
    void testDeleteById2() {
        // Arrange
        when(exerciseService.getExercisesByTopicId(Mockito.<String>any())).thenReturn(new ArrayList<>());

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
        Optional<Topic> ofResult = Optional.of(topic);
        doThrow(new RuntimeException("foo")).when(topicRepository).deleteById(Mockito.<String>any());
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> topicImpl.deleteById("42"));
        verify(exerciseService).getExercisesByTopicId(eq("42"));
        verify(topicRepository).deleteById(eq("42"));
        verify(topicRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link TopicImpl#deleteById(String)}
     */
    @Test
    void testDeleteById3() {
        // Arrange
        Optional<Topic> emptyResult = Optional.empty();
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> topicImpl.deleteById("42"));
        verify(topicRepository).findById(eq("42"));
    }
}
