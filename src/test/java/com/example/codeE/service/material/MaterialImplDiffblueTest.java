package com.example.codeE.service.material;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.helper.CloudStorageHelper;
import com.example.codeE.model.course.Course;
import com.example.codeE.model.group.Group;
import com.example.codeE.model.material.Material;
import com.example.codeE.model.material.ViewPermissionMaterial;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.repository.GroupRepository;
import com.example.codeE.repository.MaterialRepository;
import com.example.codeE.repository.TopicRepository;
import com.example.codeE.repository.ViewPermissionMaterialRepository;
import com.example.codeE.request.material.CreateMaterialRequest;
import com.example.codeE.request.material.MaterialResponse;
import com.example.codeE.request.material.UpdateMaterialRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {MaterialImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class MaterialImplDiffblueTest {
    @MockBean
    private CloudStorageHelper cloudStorageHelper;

    @MockBean
    private GroupRepository groupRepository;

    @Autowired
    private MaterialImpl materialImpl;

    @MockBean
    private MaterialRepository materialRepository;

    @MockBean
    private TopicRepository topicRepository;

    @MockBean
    private ViewPermissionMaterialRepository viewPermissionMaterialRepository;

    /**
     * Method under test: {@link MaterialImpl#createOne(CreateMaterialRequest)}
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

        Material material = new Material();
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());
        when(materialRepository.save(Mockito.<Material>any())).thenReturn(material);

        // Act
        Material actualCreateOneResult = materialImpl.createOne(new CreateMaterialRequest());

        // Assert
        verify(materialRepository).save(isA(Material.class));
        assertSame(material, actualCreateOneResult);
    }

    /**
     * Method under test: {@link MaterialImpl#createOne(CreateMaterialRequest)}
     */
    @Test
    void testCreateOne2() {
        // Arrange, Act and Assert
        assertThrows(IllegalArgumentException.class, () -> materialImpl.createOne(null));
    }

    /**
     * Method under test: {@link MaterialImpl#createOne(CreateMaterialRequest)}
     */
    @Test
    void testCreateOne3() {
        // Arrange
        when(materialRepository.save(Mockito.<Material>any())).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> materialImpl.createOne(new CreateMaterialRequest()));
        verify(materialRepository).save(isA(Material.class));
    }

    /**
     * Method under test: {@link MaterialImpl#getById(String)}
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

        Material material = new Material();
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());
        Optional<Material> ofResult = Optional.of(material);
        when(materialRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Material actualById = materialImpl.getById("42");

        // Assert
        verify(materialRepository).findById(eq("42"));
        assertSame(material, actualById);
    }

    /**
     * Method under test: {@link MaterialImpl#getById(String)}
     */
    @Test
    void testGetById2() {
        // Arrange
        Optional<Material> emptyResult = Optional.empty();
        when(materialRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> materialImpl.getById("42"));
        verify(materialRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link MaterialImpl#getById(String)}
     */
    @Test
    void testGetById3() {
        // Arrange
        when(materialRepository.findById(Mockito.<String>any())).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> materialImpl.getById("42"));
        verify(materialRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link MaterialImpl#CreateMaterial(CreateMaterialRequest, MultipartFile)}
     */
    @Test
    void testCreateMaterial() throws IOException {
        // Arrange
        Optional<Topic> emptyResult = Optional.empty();
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);
        CreateMaterialRequest createRequest = new CreateMaterialRequest();

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> materialImpl.CreateMaterial(createRequest,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))));
        verify(topicRepository).findById(isNull());
    }

    /**
     * Method under test:
     * {@link MaterialImpl#CreateMaterial(CreateMaterialRequest, MultipartFile)}
     */
    @Test
    void testCreateMaterial2() throws Exception {
        // Arrange
        when(cloudStorageHelper.uploadFile(Mockito.<MultipartFile>any(), anyBoolean(), Mockito.<String>any()))
                .thenReturn("Upload File");

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

        Material material = new Material();
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());
        when(materialRepository.save(Mockito.<Material>any())).thenReturn(material);

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
        Optional<Topic> ofResult = Optional.of(topic2);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        CreateMaterialRequest createRequest = new CreateMaterialRequest("file", "file", "42", "https://example.org/example",
                "The characteristics of someone or something",
                new MockMultipartFile("file", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Act
        Material actualCreateMaterialResult = materialImpl.CreateMaterial(createRequest,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Assert
        verify(cloudStorageHelper).uploadFile(isA(MultipartFile.class), eq(true), eq("materials/42/42/"));
        verify(topicRepository, atLeast(1)).findById(eq("42"));
        verify(materialRepository).save(isA(Material.class));
        assertEquals("Upload File", createRequest.getUrl());
        assertSame(material, actualCreateMaterialResult);
    }

    /**
     * Method under test:
     * {@link MaterialImpl#CreateMaterial(CreateMaterialRequest, MultipartFile)}
     */
    @Test
    void testCreateMaterial3() throws Exception {
        // Arrange
        when(cloudStorageHelper.uploadFile(Mockito.<MultipartFile>any(), anyBoolean(), Mockito.<String>any()))
                .thenReturn("Upload File");
        when(materialRepository.save(Mockito.<Material>any())).thenThrow(new IllegalArgumentException("file"));

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
        CreateMaterialRequest createRequest = new CreateMaterialRequest("file", "file", "42", "https://example.org/example",
                "The characteristics of someone or something",
                new MockMultipartFile("file", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> materialImpl.CreateMaterial(createRequest,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))));
        verify(cloudStorageHelper).uploadFile(isA(MultipartFile.class), eq(true), eq("materials/42/42/"));
        verify(topicRepository, atLeast(1)).findById(eq("42"));
        verify(materialRepository).save(isA(Material.class));
    }

    /**
     * Method under test:
     * {@link MaterialImpl#CreateMaterial(CreateMaterialRequest, MultipartFile)}
     */
    @Test
    void testCreateMaterial4() throws Exception {
        // Arrange
        when(cloudStorageHelper.uploadFile(Mockito.<MultipartFile>any(), anyBoolean(), Mockito.<String>any()))
                .thenReturn("Upload File");
        when(materialRepository.save(Mockito.<Material>any())).thenThrow(new NoSuchElementException("file"));

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
        CreateMaterialRequest createRequest = new CreateMaterialRequest("file", "file", "42", "https://example.org/example",
                "The characteristics of someone or something",
                new MockMultipartFile("file", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> materialImpl.CreateMaterial(createRequest,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))));
        verify(cloudStorageHelper).uploadFile(isA(MultipartFile.class), eq(true), eq("materials/42/42/"));
        verify(topicRepository, atLeast(1)).findById(eq("42"));
        verify(materialRepository).save(isA(Material.class));
    }

    /**
     * Method under test: {@link MaterialImpl#getAllByTopicId(String)}
     */
    @Test
    void testGetAllByTopicId() {
        // Arrange
        when(materialRepository.findByTopicId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<MaterialResponse> actualAllByTopicId = materialImpl.getAllByTopicId("42");

        // Assert
        verify(materialRepository).findByTopicId(eq("42"));
        assertTrue(actualAllByTopicId.isEmpty());
    }

    /**
     * Method under test: {@link MaterialImpl#getAllByTopicId(String)}
     */
    @Test
    void testGetAllByTopicId2() {
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

        Material material = new Material();
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());

        ArrayList<Material> materialList = new ArrayList<>();
        materialList.add(material);
        when(materialRepository.findByTopicId(Mockito.<String>any())).thenReturn(materialList);
        when(viewPermissionMaterialRepository.getAllGroupsByMaterialId(Mockito.<String>any()))
                .thenReturn(new ArrayList<>());

        // Act
        List<MaterialResponse> actualAllByTopicId = materialImpl.getAllByTopicId("42");

        // Assert
        verify(materialRepository).findByTopicId(eq("42"));
        verify(viewPermissionMaterialRepository).getAllGroupsByMaterialId(eq("42"));
        assertEquals(1, actualAllByTopicId.size());
    }

    /**
     * Method under test: {@link MaterialImpl#getAllByTopicId(String)}
     */
    @Test
    void testGetAllByTopicId3() {
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

        Material material = new Material();
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());

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

        Material material2 = new Material();
        material2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material2.setDescription("Description");
        material2.setMaterialId("Material Id");
        material2.setMaterialName("42");
        material2.setMaterialType("42");
        material2.setShowAll(false);
        material2.setStorageUrl("Storage Url");
        material2.setTopic(topic2);
        material2.setTopicId("Topic Id");
        material2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material2.setViewPermissionMaterials(new ArrayList<>());

        ArrayList<Material> materialList = new ArrayList<>();
        materialList.add(material2);
        materialList.add(material);
        when(materialRepository.findByTopicId(Mockito.<String>any())).thenReturn(materialList);
        when(viewPermissionMaterialRepository.getAllGroupsByMaterialId(Mockito.<String>any()))
                .thenReturn(new ArrayList<>());

        // Act
        List<MaterialResponse> actualAllByTopicId = materialImpl.getAllByTopicId("42");

        // Assert
        verify(materialRepository).findByTopicId(eq("42"));
        verify(viewPermissionMaterialRepository, atLeast(1)).getAllGroupsByMaterialId(Mockito.<String>any());
        assertEquals(2, actualAllByTopicId.size());
    }

    /**
     * Method under test: {@link MaterialImpl#getAllByTopicId(String)}
     */
    @Test
    void testGetAllByTopicId4() {
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
        Material material = mock(Material.class);
        when(material.isShowAll()).thenReturn(true);
        when(material.getDescription()).thenReturn("The characteristics of someone or something");
        when(material.getMaterialId()).thenReturn("42");
        when(material.getMaterialName()).thenReturn("Material Name");
        when(material.getMaterialType()).thenReturn("Material Type");
        when(material.getStorageUrl()).thenReturn("https://example.org/example");
        when(material.getTopicId()).thenReturn("42");
        when(material.getCreatedDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(material.getUpdatedDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        doNothing().when(material).setCreatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(material).setDescription(Mockito.<String>any());
        doNothing().when(material).setMaterialId(Mockito.<String>any());
        doNothing().when(material).setMaterialName(Mockito.<String>any());
        doNothing().when(material).setMaterialType(Mockito.<String>any());
        doNothing().when(material).setShowAll(anyBoolean());
        doNothing().when(material).setStorageUrl(Mockito.<String>any());
        doNothing().when(material).setTopic(Mockito.<Topic>any());
        doNothing().when(material).setTopicId(Mockito.<String>any());
        doNothing().when(material).setUpdatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(material).setViewPermissionMaterials(Mockito.<List<ViewPermissionMaterial>>any());
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());

        ArrayList<Material> materialList = new ArrayList<>();
        materialList.add(material);
        when(materialRepository.findByTopicId(Mockito.<String>any())).thenReturn(materialList);
        when(viewPermissionMaterialRepository.getAllGroupsByMaterialId(Mockito.<String>any()))
                .thenReturn(new ArrayList<>());

        // Act
        List<MaterialResponse> actualAllByTopicId = materialImpl.getAllByTopicId("42");

        // Assert
        verify(material).getCreatedDate();
        verify(material).getDescription();
        verify(material, atLeast(1)).getMaterialId();
        verify(material).getMaterialName();
        verify(material).getMaterialType();
        verify(material).getStorageUrl();
        verify(material).getTopicId();
        verify(material).getUpdatedDate();
        verify(material).isShowAll();
        verify(material).setCreatedDate(isA(LocalDateTime.class));
        verify(material).setDescription(eq("The characteristics of someone or something"));
        verify(material).setMaterialId(eq("42"));
        verify(material).setMaterialName(eq("Material Name"));
        verify(material).setMaterialType(eq("Material Type"));
        verify(material).setShowAll(eq(true));
        verify(material).setStorageUrl(eq("https://example.org/example"));
        verify(material).setTopic(isA(Topic.class));
        verify(material).setTopicId(eq("42"));
        verify(material).setUpdatedDate(isA(LocalDateTime.class));
        verify(material).setViewPermissionMaterials(isA(List.class));
        verify(materialRepository).findByTopicId(eq("42"));
        verify(viewPermissionMaterialRepository).getAllGroupsByMaterialId(eq("42"));
        assertEquals(1, actualAllByTopicId.size());
    }

    /**
     * Method under test: {@link MaterialImpl#getAllByTopicId(String)}
     */
    @Test
    void testGetAllByTopicId5() {
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
        Material material = mock(Material.class);
        when(material.isShowAll()).thenReturn(true);
        when(material.getDescription()).thenReturn("The characteristics of someone or something");
        when(material.getMaterialId()).thenReturn("42");
        when(material.getMaterialName()).thenReturn("Material Name");
        when(material.getMaterialType()).thenReturn("Material Type");
        when(material.getStorageUrl()).thenReturn("https://example.org/example");
        when(material.getTopicId()).thenReturn("42");
        when(material.getCreatedDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(material.getUpdatedDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        doNothing().when(material).setCreatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(material).setDescription(Mockito.<String>any());
        doNothing().when(material).setMaterialId(Mockito.<String>any());
        doNothing().when(material).setMaterialName(Mockito.<String>any());
        doNothing().when(material).setMaterialType(Mockito.<String>any());
        doNothing().when(material).setShowAll(anyBoolean());
        doNothing().when(material).setStorageUrl(Mockito.<String>any());
        doNothing().when(material).setTopic(Mockito.<Topic>any());
        doNothing().when(material).setTopicId(Mockito.<String>any());
        doNothing().when(material).setUpdatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(material).setViewPermissionMaterials(Mockito.<List<ViewPermissionMaterial>>any());
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());

        ArrayList<Material> materialList = new ArrayList<>();
        materialList.add(material);
        when(materialRepository.findByTopicId(Mockito.<String>any())).thenReturn(materialList);

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

        Material material2 = new Material();
        material2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material2.setDescription("The characteristics of someone or something");
        material2.setMaterialId("42");
        material2.setMaterialName("Material Name");
        material2.setMaterialType("Material Type");
        material2.setShowAll(true);
        material2.setStorageUrl("https://example.org/example");
        material2.setTopic(topic2);
        material2.setTopicId("42");
        material2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material2.setViewPermissionMaterials(new ArrayList<>());

        ViewPermissionMaterial viewPermissionMaterial = new ViewPermissionMaterial();
        viewPermissionMaterial.setGroup(group2);
        viewPermissionMaterial.setGroupId("42");
        viewPermissionMaterial.setMaterial(material2);
        viewPermissionMaterial.setMaterialId("42");

        ArrayList<ViewPermissionMaterial> viewPermissionMaterialList = new ArrayList<>();
        viewPermissionMaterialList.add(viewPermissionMaterial);
        when(viewPermissionMaterialRepository.getAllGroupsByMaterialId(Mockito.<String>any()))
                .thenReturn(viewPermissionMaterialList);

        // Act
        List<MaterialResponse> actualAllByTopicId = materialImpl.getAllByTopicId("42");

        // Assert
        verify(material).getCreatedDate();
        verify(material).getDescription();
        verify(material, atLeast(1)).getMaterialId();
        verify(material).getMaterialName();
        verify(material).getMaterialType();
        verify(material).getStorageUrl();
        verify(material).getTopicId();
        verify(material).getUpdatedDate();
        verify(material).isShowAll();
        verify(material).setCreatedDate(isA(LocalDateTime.class));
        verify(material).setDescription(eq("The characteristics of someone or something"));
        verify(material).setMaterialId(eq("42"));
        verify(material).setMaterialName(eq("Material Name"));
        verify(material).setMaterialType(eq("Material Type"));
        verify(material).setShowAll(eq(true));
        verify(material).setStorageUrl(eq("https://example.org/example"));
        verify(material).setTopic(isA(Topic.class));
        verify(material).setTopicId(eq("42"));
        verify(material).setUpdatedDate(isA(LocalDateTime.class));
        verify(material).setViewPermissionMaterials(isA(List.class));
        verify(materialRepository).findByTopicId(eq("42"));
        verify(viewPermissionMaterialRepository).getAllGroupsByMaterialId(eq("42"));
        verify(groupRepository).findById(eq("42"));
        assertEquals(1, actualAllByTopicId.size());
    }

    /**
     * Method under test: {@link MaterialImpl#getAll()}
     */
    @Test
    void testGetAll() {
        // Arrange
        ArrayList<Material> materialList = new ArrayList<>();
        when(materialRepository.findAll()).thenReturn(materialList);

        // Act
        List<Material> actualAll = materialImpl.getAll();

        // Assert
        verify(materialRepository).findAll();
        assertTrue(actualAll.isEmpty());
        assertSame(materialList, actualAll);
    }

    /**
     * Method under test: {@link MaterialImpl#getAll()}
     */
    @Test
    void testGetAll2() {
        // Arrange
        when(materialRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> materialImpl.getAll());
        verify(materialRepository).findAll();
    }

    /**
     * Method under test: {@link MaterialImpl#deleteById(String)}
     */
    @Test
    void testDeleteById() {
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

        Material material = new Material();
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());
        Optional<Material> ofResult = Optional.of(material);
        doNothing().when(materialRepository).deleteById(Mockito.<String>any());
        when(materialRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        materialImpl.deleteById("42");

        // Assert that nothing has changed
        verify(materialRepository).deleteById(eq("42"));
        verify(materialRepository).findById(eq("42"));
        assertTrue(materialImpl.getAll().isEmpty());
    }

    /**
     * Method under test: {@link MaterialImpl#deleteById(String)}
     */
    @Test
    void testDeleteById2() {
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

        Material material = new Material();
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());
        Optional<Material> ofResult = Optional.of(material);
        doThrow(new IllegalArgumentException("file")).when(materialRepository).deleteById(Mockito.<String>any());
        when(materialRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> materialImpl.deleteById("42"));
        verify(materialRepository).deleteById(eq("42"));
        verify(materialRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link MaterialImpl#deleteById(String)}
     */
    @Test
    void testDeleteById3() {
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
        Material material = mock(Material.class);
        when(material.getMaterialType()).thenReturn("Material Type");
        doNothing().when(material).setCreatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(material).setDescription(Mockito.<String>any());
        doNothing().when(material).setMaterialId(Mockito.<String>any());
        doNothing().when(material).setMaterialName(Mockito.<String>any());
        doNothing().when(material).setMaterialType(Mockito.<String>any());
        doNothing().when(material).setShowAll(anyBoolean());
        doNothing().when(material).setStorageUrl(Mockito.<String>any());
        doNothing().when(material).setTopic(Mockito.<Topic>any());
        doNothing().when(material).setTopicId(Mockito.<String>any());
        doNothing().when(material).setUpdatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(material).setViewPermissionMaterials(Mockito.<List<ViewPermissionMaterial>>any());
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());
        Optional<Material> ofResult = Optional.of(material);
        doNothing().when(materialRepository).deleteById(Mockito.<String>any());
        when(materialRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        materialImpl.deleteById("42");

        // Assert that nothing has changed
        verify(material).getMaterialType();
        verify(material).setCreatedDate(isA(LocalDateTime.class));
        verify(material).setDescription(eq("The characteristics of someone or something"));
        verify(material).setMaterialId(eq("42"));
        verify(material).setMaterialName(eq("Material Name"));
        verify(material).setMaterialType(eq("Material Type"));
        verify(material).setShowAll(eq(true));
        verify(material).setStorageUrl(eq("https://example.org/example"));
        verify(material).setTopic(isA(Topic.class));
        verify(material).setTopicId(eq("42"));
        verify(material).setUpdatedDate(isA(LocalDateTime.class));
        verify(material).setViewPermissionMaterials(isA(List.class));
        verify(materialRepository).deleteById(eq("42"));
        verify(materialRepository).findById(eq("42"));
        assertTrue(materialImpl.getAll().isEmpty());
    }

    /**
     * Method under test: {@link MaterialImpl#deleteById(String)}
     */
    @Test
    void testDeleteById4() throws IOException, RuntimeException {
        // Arrange
        when(cloudStorageHelper.deleteFile(Mockito.<String>any())).thenReturn(true);

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
        Material material = mock(Material.class);
        when(material.getStorageUrl()).thenReturn("https://example.org/example");
        when(material.getMaterialType()).thenReturn("file");
        doNothing().when(material).setCreatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(material).setDescription(Mockito.<String>any());
        doNothing().when(material).setMaterialId(Mockito.<String>any());
        doNothing().when(material).setMaterialName(Mockito.<String>any());
        doNothing().when(material).setMaterialType(Mockito.<String>any());
        doNothing().when(material).setShowAll(anyBoolean());
        doNothing().when(material).setStorageUrl(Mockito.<String>any());
        doNothing().when(material).setTopic(Mockito.<Topic>any());
        doNothing().when(material).setTopicId(Mockito.<String>any());
        doNothing().when(material).setUpdatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(material).setViewPermissionMaterials(Mockito.<List<ViewPermissionMaterial>>any());
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());
        Optional<Material> ofResult = Optional.of(material);
        doNothing().when(materialRepository).deleteById(Mockito.<String>any());
        when(materialRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        materialImpl.deleteById("42");

        // Assert that nothing has changed
        verify(cloudStorageHelper).deleteFile(eq("https://example.org/example"));
        verify(material).getMaterialType();
        verify(material).getStorageUrl();
        verify(material).setCreatedDate(isA(LocalDateTime.class));
        verify(material).setDescription(eq("The characteristics of someone or something"));
        verify(material).setMaterialId(eq("42"));
        verify(material).setMaterialName(eq("Material Name"));
        verify(material).setMaterialType(eq("Material Type"));
        verify(material).setShowAll(eq(true));
        verify(material).setStorageUrl(eq("https://example.org/example"));
        verify(material).setTopic(isA(Topic.class));
        verify(material).setTopicId(eq("42"));
        verify(material).setUpdatedDate(isA(LocalDateTime.class));
        verify(material).setViewPermissionMaterials(isA(List.class));
        verify(materialRepository).deleteById(eq("42"));
        verify(materialRepository).findById(eq("42"));
        assertTrue(materialImpl.getAll().isEmpty());
    }

    /**
     * Method under test: {@link MaterialImpl#deleteById(String)}
     */
    @Test
    void testDeleteById5() {
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
        Material material = mock(Material.class);
        when(material.getStorageUrl()).thenThrow(new IllegalArgumentException("file"));
        when(material.getMaterialType()).thenReturn("file");
        doNothing().when(material).setCreatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(material).setDescription(Mockito.<String>any());
        doNothing().when(material).setMaterialId(Mockito.<String>any());
        doNothing().when(material).setMaterialName(Mockito.<String>any());
        doNothing().when(material).setMaterialType(Mockito.<String>any());
        doNothing().when(material).setShowAll(anyBoolean());
        doNothing().when(material).setStorageUrl(Mockito.<String>any());
        doNothing().when(material).setTopic(Mockito.<Topic>any());
        doNothing().when(material).setTopicId(Mockito.<String>any());
        doNothing().when(material).setUpdatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(material).setViewPermissionMaterials(Mockito.<List<ViewPermissionMaterial>>any());
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());
        Optional<Material> ofResult = Optional.of(material);
        when(materialRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> materialImpl.deleteById("42"));
        verify(material).getMaterialType();
        verify(material).getStorageUrl();
        verify(material).setCreatedDate(isA(LocalDateTime.class));
        verify(material).setDescription(eq("The characteristics of someone or something"));
        verify(material).setMaterialId(eq("42"));
        verify(material).setMaterialName(eq("Material Name"));
        verify(material).setMaterialType(eq("Material Type"));
        verify(material).setShowAll(eq(true));
        verify(material).setStorageUrl(eq("https://example.org/example"));
        verify(material).setTopic(isA(Topic.class));
        verify(material).setTopicId(eq("42"));
        verify(material).setUpdatedDate(isA(LocalDateTime.class));
        verify(material).setViewPermissionMaterials(isA(List.class));
        verify(materialRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link MaterialImpl#deleteById(String)}
     */
    @Test
    void testDeleteById6() throws IOException, RuntimeException {
        // Arrange
        when(cloudStorageHelper.deleteFile(Mockito.<String>any())).thenReturn(false);

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
        Material material = mock(Material.class);
        when(material.getStorageUrl()).thenReturn("https://example.org/example");
        when(material.getMaterialType()).thenReturn("file");
        doNothing().when(material).setCreatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(material).setDescription(Mockito.<String>any());
        doNothing().when(material).setMaterialId(Mockito.<String>any());
        doNothing().when(material).setMaterialName(Mockito.<String>any());
        doNothing().when(material).setMaterialType(Mockito.<String>any());
        doNothing().when(material).setShowAll(anyBoolean());
        doNothing().when(material).setStorageUrl(Mockito.<String>any());
        doNothing().when(material).setTopic(Mockito.<Topic>any());
        doNothing().when(material).setTopicId(Mockito.<String>any());
        doNothing().when(material).setUpdatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(material).setViewPermissionMaterials(Mockito.<List<ViewPermissionMaterial>>any());
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());
        Optional<Material> ofResult = Optional.of(material);
        when(materialRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> materialImpl.deleteById("42"));
        verify(cloudStorageHelper).deleteFile(eq("https://example.org/example"));
        verify(material).getMaterialType();
        verify(material).getStorageUrl();
        verify(material).setCreatedDate(isA(LocalDateTime.class));
        verify(material).setDescription(eq("The characteristics of someone or something"));
        verify(material).setMaterialId(eq("42"));
        verify(material).setMaterialName(eq("Material Name"));
        verify(material).setMaterialType(eq("Material Type"));
        verify(material).setShowAll(eq(true));
        verify(material).setStorageUrl(eq("https://example.org/example"));
        verify(material).setTopic(isA(Topic.class));
        verify(material).setTopicId(eq("42"));
        verify(material).setUpdatedDate(isA(LocalDateTime.class));
        verify(material).setViewPermissionMaterials(isA(List.class));
        verify(materialRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link MaterialImpl#deleteById(String)}
     */
    @Test
    void testDeleteById7() {
        // Arrange
        Optional<Material> emptyResult = Optional.empty();
        when(materialRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> materialImpl.deleteById("42"));
        verify(materialRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link MaterialImpl#updateById(UpdateMaterialRequest)}
     */
    @Test
    void testUpdateById() {
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

        Material material = new Material();
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());
        Optional<Material> ofResult = Optional.of(material);

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

        Material material2 = new Material();
        material2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material2.setDescription("The characteristics of someone or something");
        material2.setMaterialId("42");
        material2.setMaterialName("Material Name");
        material2.setMaterialType("Material Type");
        material2.setShowAll(true);
        material2.setStorageUrl("https://example.org/example");
        material2.setTopic(topic2);
        material2.setTopicId("42");
        material2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material2.setViewPermissionMaterials(new ArrayList<>());
        when(materialRepository.save(Mockito.<Material>any())).thenReturn(material2);
        when(materialRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Material actualUpdateByIdResult = materialImpl
                .updateById(new UpdateMaterialRequest("42", "The characteristics of someone or something", true));

        // Assert
        verify(materialRepository).findById(eq("42"));
        verify(materialRepository).save(isA(Material.class));
        assertSame(material2, actualUpdateByIdResult);
    }

    /**
     * Method under test: {@link MaterialImpl#updateById(UpdateMaterialRequest)}
     */
    @Test
    void testUpdateById2() {
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

        Material material = new Material();
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());
        Optional<Material> ofResult = Optional.of(material);
        when(materialRepository.save(Mockito.<Material>any())).thenThrow(new IllegalArgumentException("foo"));
        when(materialRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> materialImpl
                .updateById(new UpdateMaterialRequest("42", "The characteristics of someone or something", true)));
        verify(materialRepository).findById(eq("42"));
        verify(materialRepository).save(isA(Material.class));
    }

    /**
     * Method under test: {@link MaterialImpl#updateById(UpdateMaterialRequest)}
     */
    @Test
    void testUpdateById3() {
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
        Material material = mock(Material.class);
        doNothing().when(material).setCreatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(material).setDescription(Mockito.<String>any());
        doNothing().when(material).setMaterialId(Mockito.<String>any());
        doNothing().when(material).setMaterialName(Mockito.<String>any());
        doNothing().when(material).setMaterialType(Mockito.<String>any());
        doNothing().when(material).setShowAll(anyBoolean());
        doNothing().when(material).setStorageUrl(Mockito.<String>any());
        doNothing().when(material).setTopic(Mockito.<Topic>any());
        doNothing().when(material).setTopicId(Mockito.<String>any());
        doNothing().when(material).setUpdatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(material).setViewPermissionMaterials(Mockito.<List<ViewPermissionMaterial>>any());
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());
        Optional<Material> ofResult = Optional.of(material);

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

        Material material2 = new Material();
        material2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material2.setDescription("The characteristics of someone or something");
        material2.setMaterialId("42");
        material2.setMaterialName("Material Name");
        material2.setMaterialType("Material Type");
        material2.setShowAll(true);
        material2.setStorageUrl("https://example.org/example");
        material2.setTopic(topic2);
        material2.setTopicId("42");
        material2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material2.setViewPermissionMaterials(new ArrayList<>());
        when(materialRepository.save(Mockito.<Material>any())).thenReturn(material2);
        when(materialRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Material actualUpdateByIdResult = materialImpl
                .updateById(new UpdateMaterialRequest("42", "The characteristics of someone or something", true));

        // Assert
        verify(material).setCreatedDate(isA(LocalDateTime.class));
        verify(material, atLeast(1)).setDescription(eq("The characteristics of someone or something"));
        verify(material).setMaterialId(eq("42"));
        verify(material).setMaterialName(eq("Material Name"));
        verify(material).setMaterialType(eq("Material Type"));
        verify(material).setShowAll(eq(true));
        verify(material).setStorageUrl(eq("https://example.org/example"));
        verify(material).setTopic(isA(Topic.class));
        verify(material).setTopicId(eq("42"));
        verify(material).setUpdatedDate(isA(LocalDateTime.class));
        verify(material).setViewPermissionMaterials(isA(List.class));
        verify(materialRepository).findById(eq("42"));
        verify(materialRepository).save(isA(Material.class));
        assertSame(material2, actualUpdateByIdResult);
    }

    /**
     * Method under test: {@link MaterialImpl#removeViewPermission(String, List)}
     */
    @Test
    void testRemoveViewPermission() {
        // Arrange
        when(materialRepository.existsById(Mockito.<String>any())).thenReturn(true);

        // Act
        boolean actualRemoveViewPermissionResult = materialImpl.removeViewPermission("42", new ArrayList<>());

        // Assert
        verify(materialRepository).existsById(eq("42"));
        assertTrue(actualRemoveViewPermissionResult);
    }

    /**
     * Method under test: {@link MaterialImpl#removeViewPermission(String, List)}
     */
    @Test
    void testRemoveViewPermission2() {
        // Arrange
        when(materialRepository.existsById(Mockito.<String>any())).thenReturn(false);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> materialImpl.removeViewPermission("42", new ArrayList<>()));
        verify(materialRepository).existsById(eq("42"));
    }

    /**
     * Method under test: {@link MaterialImpl#removeViewPermission(String, List)}
     */
    @Test
    void testRemoveViewPermission3() {
        // Arrange
        when(groupRepository.existsById(Mockito.<String>any())).thenReturn(true);
        when(materialRepository.existsById(Mockito.<String>any())).thenReturn(true);
        doNothing().when(viewPermissionMaterialRepository)
                .removeViewPermission(Mockito.<String>any(), Mockito.<String>any());

        ArrayList<String> groupIds = new ArrayList<>();
        groupIds.add("foo");

        // Act
        boolean actualRemoveViewPermissionResult = materialImpl.removeViewPermission("42", groupIds);

        // Assert
        verify(viewPermissionMaterialRepository).removeViewPermission(eq("42"), eq("foo"));
        verify(materialRepository).existsById(eq("42"));
        verify(groupRepository).existsById(eq("foo"));
        assertTrue(actualRemoveViewPermissionResult);
    }

    /**
     * Method under test: {@link MaterialImpl#removeViewPermission(String, List)}
     */
    @Test
    void testRemoveViewPermission4() {
        // Arrange
        when(groupRepository.existsById(Mockito.<String>any())).thenReturn(true);
        when(materialRepository.existsById(Mockito.<String>any())).thenReturn(true);
        doThrow(new IllegalArgumentException("foo")).when(viewPermissionMaterialRepository)
                .removeViewPermission(Mockito.<String>any(), Mockito.<String>any());

        ArrayList<String> groupIds = new ArrayList<>();
        groupIds.add("foo");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> materialImpl.removeViewPermission("42", groupIds));
        verify(viewPermissionMaterialRepository).removeViewPermission(eq("42"), eq("foo"));
        verify(materialRepository).existsById(eq("42"));
        verify(groupRepository).existsById(eq("foo"));
    }

    /**
     * Method under test: {@link MaterialImpl#removeViewPermission(String, List)}
     */
    @Test
    void testRemoveViewPermission5() {
        // Arrange
        when(groupRepository.existsById(Mockito.<String>any())).thenReturn(false);
        when(materialRepository.existsById(Mockito.<String>any())).thenReturn(true);

        ArrayList<String> groupIds = new ArrayList<>();
        groupIds.add("foo");

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> materialImpl.removeViewPermission("42", groupIds));
        verify(materialRepository).existsById(eq("42"));
        verify(groupRepository).existsById(eq("foo"));
    }

    /**
     * Method under test: {@link MaterialImpl#removeViewPermission(String, List)}
     */
    @Test
    void testRemoveViewPermission6() {
        // Arrange
        when(groupRepository.existsById(Mockito.<String>any())).thenReturn(true);
        when(materialRepository.existsById(Mockito.<String>any())).thenReturn(true);
        doNothing().when(viewPermissionMaterialRepository)
                .removeViewPermission(Mockito.<String>any(), Mockito.<String>any());

        ArrayList<String> groupIds = new ArrayList<>();
        groupIds.add("42");
        groupIds.add("foo");

        // Act
        boolean actualRemoveViewPermissionResult = materialImpl.removeViewPermission("42", groupIds);

        // Assert
        verify(viewPermissionMaterialRepository, atLeast(1)).removeViewPermission(eq("42"), Mockito.<String>any());
        verify(groupRepository, atLeast(1)).existsById(Mockito.<String>any());
        verify(materialRepository).existsById(eq("42"));
        assertTrue(actualRemoveViewPermissionResult);
    }

    /**
     * Method under test:
     * {@link MaterialImpl#addViewPermission(String, List, boolean)}
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

        Material material = new Material();
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());
        Optional<Material> ofResult = Optional.of(material);

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

        Material material2 = new Material();
        material2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material2.setDescription("The characteristics of someone or something");
        material2.setMaterialId("42");
        material2.setMaterialName("Material Name");
        material2.setMaterialType("Material Type");
        material2.setShowAll(true);
        material2.setStorageUrl("https://example.org/example");
        material2.setTopic(topic2);
        material2.setTopicId("42");
        material2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material2.setViewPermissionMaterials(new ArrayList<>());
        when(materialRepository.save(Mockito.<Material>any())).thenReturn(material2);
        when(materialRepository.existsById(Mockito.<String>any())).thenReturn(true);
        when(materialRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        boolean actualAddViewPermissionResult = materialImpl.addViewPermission("42", new ArrayList<>(), true);

        // Assert
        verify(materialRepository).existsById(eq("42"));
        verify(materialRepository).findById(eq("42"));
        verify(materialRepository).save(isA(Material.class));
        assertTrue(actualAddViewPermissionResult);
    }

    /**
     * Method under test:
     * {@link MaterialImpl#addViewPermission(String, List, boolean)}
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

        Material material = new Material();
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());
        Optional<Material> ofResult = Optional.of(material);
        when(materialRepository.save(Mockito.<Material>any())).thenThrow(new IllegalArgumentException("foo"));
        when(materialRepository.existsById(Mockito.<String>any())).thenReturn(true);
        when(materialRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> materialImpl.addViewPermission("42", new ArrayList<>(), true));
        verify(materialRepository).existsById(eq("42"));
        verify(materialRepository).findById(eq("42"));
        verify(materialRepository).save(isA(Material.class));
    }

    /**
     * Method under test:
     * {@link MaterialImpl#addViewPermission(String, List, boolean)}
     */
    @Test
    void testAddViewPermission3() {
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

        Material material = new Material();
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());
        Optional<Material> ofResult = Optional.of(material);
        when(materialRepository.existsById(Mockito.<String>any())).thenReturn(false);
        when(materialRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> materialImpl.addViewPermission("42", new ArrayList<>(), true));
        verify(materialRepository).existsById(eq("42"));
        verify(materialRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link MaterialImpl#addViewPermission(String, List, boolean)}
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
        Material material = mock(Material.class);
        doNothing().when(material).setCreatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(material).setDescription(Mockito.<String>any());
        doNothing().when(material).setMaterialId(Mockito.<String>any());
        doNothing().when(material).setMaterialName(Mockito.<String>any());
        doNothing().when(material).setMaterialType(Mockito.<String>any());
        doNothing().when(material).setShowAll(anyBoolean());
        doNothing().when(material).setStorageUrl(Mockito.<String>any());
        doNothing().when(material).setTopic(Mockito.<Topic>any());
        doNothing().when(material).setTopicId(Mockito.<String>any());
        doNothing().when(material).setUpdatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(material).setViewPermissionMaterials(Mockito.<List<ViewPermissionMaterial>>any());
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());
        Optional<Material> ofResult = Optional.of(material);

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

        Material material2 = new Material();
        material2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material2.setDescription("The characteristics of someone or something");
        material2.setMaterialId("42");
        material2.setMaterialName("Material Name");
        material2.setMaterialType("Material Type");
        material2.setShowAll(true);
        material2.setStorageUrl("https://example.org/example");
        material2.setTopic(topic2);
        material2.setTopicId("42");
        material2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material2.setViewPermissionMaterials(new ArrayList<>());
        when(materialRepository.save(Mockito.<Material>any())).thenReturn(material2);
        when(materialRepository.existsById(Mockito.<String>any())).thenReturn(true);
        when(materialRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        boolean actualAddViewPermissionResult = materialImpl.addViewPermission("42", new ArrayList<>(), true);

        // Assert
        verify(material).setCreatedDate(isA(LocalDateTime.class));
        verify(material).setDescription(eq("The characteristics of someone or something"));
        verify(material).setMaterialId(eq("42"));
        verify(material).setMaterialName(eq("Material Name"));
        verify(material).setMaterialType(eq("Material Type"));
        verify(material, atLeast(1)).setShowAll(eq(true));
        verify(material).setStorageUrl(eq("https://example.org/example"));
        verify(material).setTopic(isA(Topic.class));
        verify(material).setTopicId(eq("42"));
        verify(material).setUpdatedDate(isA(LocalDateTime.class));
        verify(material).setViewPermissionMaterials(isA(List.class));
        verify(materialRepository).existsById(eq("42"));
        verify(materialRepository).findById(eq("42"));
        verify(materialRepository).save(isA(Material.class));
        assertTrue(actualAddViewPermissionResult);
    }

    /**
     * Method under test: {@link MaterialImpl#getMaterialByUserId(String, String)}
     */
    @Test
    void testGetMaterialByUserId() {
        // Arrange
        when(materialRepository.getMaterialById(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(new ArrayList<>());

        // Act
        List<MaterialResponse> actualMaterialByUserId = materialImpl.getMaterialByUserId("42", "42");

        // Assert
        verify(materialRepository).getMaterialById(eq("42"), eq("42"));
        assertTrue(actualMaterialByUserId.isEmpty());
    }

    /**
     * Method under test: {@link MaterialImpl#getMaterialByUserId(String, String)}
     */
    @Test
    void testGetMaterialByUserId2() {
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

        Material material = new Material();
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());

        ArrayList<Material> materialList = new ArrayList<>();
        materialList.add(material);
        when(materialRepository.getMaterialById(Mockito.<String>any(), Mockito.<String>any())).thenReturn(materialList);
        when(viewPermissionMaterialRepository.getAllGroupsByMaterialId(Mockito.<String>any()))
                .thenReturn(new ArrayList<>());

        // Act
        List<MaterialResponse> actualMaterialByUserId = materialImpl.getMaterialByUserId("42", "42");

        // Assert
        verify(materialRepository).getMaterialById(eq("42"), eq("42"));
        verify(viewPermissionMaterialRepository).getAllGroupsByMaterialId(eq("42"));
        assertEquals(1, actualMaterialByUserId.size());
    }

    /**
     * Method under test: {@link MaterialImpl#getMaterialByUserId(String, String)}
     */
    @Test
    void testGetMaterialByUserId3() {
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

        Material material = new Material();
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());

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

        Material material2 = new Material();
        material2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material2.setDescription("Description");
        material2.setMaterialId("Material Id");
        material2.setMaterialName("42");
        material2.setMaterialType("42");
        material2.setShowAll(false);
        material2.setStorageUrl("Storage Url");
        material2.setTopic(topic2);
        material2.setTopicId("Topic Id");
        material2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material2.setViewPermissionMaterials(new ArrayList<>());

        ArrayList<Material> materialList = new ArrayList<>();
        materialList.add(material2);
        materialList.add(material);
        when(materialRepository.getMaterialById(Mockito.<String>any(), Mockito.<String>any())).thenReturn(materialList);
        when(viewPermissionMaterialRepository.getAllGroupsByMaterialId(Mockito.<String>any()))
                .thenReturn(new ArrayList<>());

        // Act
        List<MaterialResponse> actualMaterialByUserId = materialImpl.getMaterialByUserId("42", "42");

        // Assert
        verify(materialRepository).getMaterialById(eq("42"), eq("42"));
        verify(viewPermissionMaterialRepository, atLeast(1)).getAllGroupsByMaterialId(Mockito.<String>any());
        assertEquals(2, actualMaterialByUserId.size());
    }

    /**
     * Method under test: {@link MaterialImpl#getMaterialByUserId(String, String)}
     */
    @Test
    void testGetMaterialByUserId4() {
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
        Material material = mock(Material.class);
        when(material.isShowAll()).thenReturn(true);
        when(material.getDescription()).thenReturn("The characteristics of someone or something");
        when(material.getMaterialId()).thenReturn("42");
        when(material.getMaterialName()).thenReturn("Material Name");
        when(material.getMaterialType()).thenReturn("Material Type");
        when(material.getStorageUrl()).thenReturn("https://example.org/example");
        when(material.getTopicId()).thenReturn("42");
        when(material.getCreatedDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(material.getUpdatedDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        doNothing().when(material).setCreatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(material).setDescription(Mockito.<String>any());
        doNothing().when(material).setMaterialId(Mockito.<String>any());
        doNothing().when(material).setMaterialName(Mockito.<String>any());
        doNothing().when(material).setMaterialType(Mockito.<String>any());
        doNothing().when(material).setShowAll(anyBoolean());
        doNothing().when(material).setStorageUrl(Mockito.<String>any());
        doNothing().when(material).setTopic(Mockito.<Topic>any());
        doNothing().when(material).setTopicId(Mockito.<String>any());
        doNothing().when(material).setUpdatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(material).setViewPermissionMaterials(Mockito.<List<ViewPermissionMaterial>>any());
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());

        ArrayList<Material> materialList = new ArrayList<>();
        materialList.add(material);
        when(materialRepository.getMaterialById(Mockito.<String>any(), Mockito.<String>any())).thenReturn(materialList);
        when(viewPermissionMaterialRepository.getAllGroupsByMaterialId(Mockito.<String>any()))
                .thenReturn(new ArrayList<>());

        // Act
        List<MaterialResponse> actualMaterialByUserId = materialImpl.getMaterialByUserId("42", "42");

        // Assert
        verify(material).getCreatedDate();
        verify(material).getDescription();
        verify(material, atLeast(1)).getMaterialId();
        verify(material).getMaterialName();
        verify(material).getMaterialType();
        verify(material).getStorageUrl();
        verify(material).getTopicId();
        verify(material).getUpdatedDate();
        verify(material).isShowAll();
        verify(material).setCreatedDate(isA(LocalDateTime.class));
        verify(material).setDescription(eq("The characteristics of someone or something"));
        verify(material).setMaterialId(eq("42"));
        verify(material).setMaterialName(eq("Material Name"));
        verify(material).setMaterialType(eq("Material Type"));
        verify(material).setShowAll(eq(true));
        verify(material).setStorageUrl(eq("https://example.org/example"));
        verify(material).setTopic(isA(Topic.class));
        verify(material).setTopicId(eq("42"));
        verify(material).setUpdatedDate(isA(LocalDateTime.class));
        verify(material).setViewPermissionMaterials(isA(List.class));
        verify(materialRepository).getMaterialById(eq("42"), eq("42"));
        verify(viewPermissionMaterialRepository).getAllGroupsByMaterialId(eq("42"));
        assertEquals(1, actualMaterialByUserId.size());
    }

    /**
     * Method under test: {@link MaterialImpl#getMaterialByUserId(String, String)}
     */
    @Test
    void testGetMaterialByUserId5() {
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
        Material material = mock(Material.class);
        when(material.isShowAll()).thenReturn(true);
        when(material.getDescription()).thenReturn("The characteristics of someone or something");
        when(material.getMaterialId()).thenReturn("42");
        when(material.getMaterialName()).thenReturn("Material Name");
        when(material.getMaterialType()).thenReturn("Material Type");
        when(material.getStorageUrl()).thenReturn("https://example.org/example");
        when(material.getTopicId()).thenReturn("42");
        when(material.getCreatedDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(material.getUpdatedDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        doNothing().when(material).setCreatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(material).setDescription(Mockito.<String>any());
        doNothing().when(material).setMaterialId(Mockito.<String>any());
        doNothing().when(material).setMaterialName(Mockito.<String>any());
        doNothing().when(material).setMaterialType(Mockito.<String>any());
        doNothing().when(material).setShowAll(anyBoolean());
        doNothing().when(material).setStorageUrl(Mockito.<String>any());
        doNothing().when(material).setTopic(Mockito.<Topic>any());
        doNothing().when(material).setTopicId(Mockito.<String>any());
        doNothing().when(material).setUpdatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(material).setViewPermissionMaterials(Mockito.<List<ViewPermissionMaterial>>any());
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());

        ArrayList<Material> materialList = new ArrayList<>();
        materialList.add(material);
        when(materialRepository.getMaterialById(Mockito.<String>any(), Mockito.<String>any())).thenReturn(materialList);

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

        Material material2 = new Material();
        material2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material2.setDescription("The characteristics of someone or something");
        material2.setMaterialId("42");
        material2.setMaterialName("Material Name");
        material2.setMaterialType("Material Type");
        material2.setShowAll(true);
        material2.setStorageUrl("https://example.org/example");
        material2.setTopic(topic2);
        material2.setTopicId("42");
        material2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material2.setViewPermissionMaterials(new ArrayList<>());

        ViewPermissionMaterial viewPermissionMaterial = new ViewPermissionMaterial();
        viewPermissionMaterial.setGroup(group2);
        viewPermissionMaterial.setGroupId("42");
        viewPermissionMaterial.setMaterial(material2);
        viewPermissionMaterial.setMaterialId("42");

        ArrayList<ViewPermissionMaterial> viewPermissionMaterialList = new ArrayList<>();
        viewPermissionMaterialList.add(viewPermissionMaterial);
        when(viewPermissionMaterialRepository.getAllGroupsByMaterialId(Mockito.<String>any()))
                .thenReturn(viewPermissionMaterialList);

        // Act
        List<MaterialResponse> actualMaterialByUserId = materialImpl.getMaterialByUserId("42", "42");

        // Assert
        verify(material).getCreatedDate();
        verify(material).getDescription();
        verify(material, atLeast(1)).getMaterialId();
        verify(material).getMaterialName();
        verify(material).getMaterialType();
        verify(material).getStorageUrl();
        verify(material).getTopicId();
        verify(material).getUpdatedDate();
        verify(material).isShowAll();
        verify(material).setCreatedDate(isA(LocalDateTime.class));
        verify(material).setDescription(eq("The characteristics of someone or something"));
        verify(material).setMaterialId(eq("42"));
        verify(material).setMaterialName(eq("Material Name"));
        verify(material).setMaterialType(eq("Material Type"));
        verify(material).setShowAll(eq(true));
        verify(material).setStorageUrl(eq("https://example.org/example"));
        verify(material).setTopic(isA(Topic.class));
        verify(material).setTopicId(eq("42"));
        verify(material).setUpdatedDate(isA(LocalDateTime.class));
        verify(material).setViewPermissionMaterials(isA(List.class));
        verify(materialRepository).getMaterialById(eq("42"), eq("42"));
        verify(viewPermissionMaterialRepository).getAllGroupsByMaterialId(eq("42"));
        verify(groupRepository).findById(eq("42"));
        assertEquals(1, actualMaterialByUserId.size());
    }
}
