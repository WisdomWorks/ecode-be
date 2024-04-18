package com.example.codeE.service.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import com.example.codeE.entity.group.StudentInGroupEntity;
import com.example.codeE.entity.group.StudentNotInGroupEntity;
import com.example.codeE.model.course.Course;
import com.example.codeE.model.group.Group;
import com.example.codeE.model.group.GroupStudent;
import com.example.codeE.model.group.UpdateGroupRequest;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.GroupRepository;
import com.example.codeE.repository.GroupStudentRepository;
import com.example.codeE.request.group.CreateGroupRequest;
import com.example.codeE.service.course.CourseService;

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

@ContextConfiguration(classes = {GroupImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class GroupImplDiffblueTest {
    @MockBean
    private CourseService courseService;

    @Autowired
    private GroupImpl groupImpl;

    @MockBean
    private GroupRepository groupRepository;

    @MockBean
    private GroupStudentRepository groupStudentRepository;

    /**
     * Method under test: {@link GroupImpl#createOne(CreateGroupRequest)}
     */
    @Test
    void testCreateOne() {
        // Arrange
        when(courseService.checkCourseExistById(Mockito.<String>any())).thenReturn(true);

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
        when(groupRepository.save(Mockito.<Group>any())).thenReturn(group);

        // Act
        Group actualCreateOneResult = groupImpl.createOne(new CreateGroupRequest("42", "Group Name"));

        // Assert
        verify(courseService).checkCourseExistById(eq("42"));
        verify(groupRepository).save(isA(Group.class));
        LocalTime expectedToLocalTimeResult = actualCreateOneResult.getCreateDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualCreateOneResult.getCourse().getUpdatedDate().toLocalTime());
    }

    /**
     * Method under test: {@link GroupImpl#createOne(CreateGroupRequest)}
     */
    @Test
    void testCreateOne2() {
        // Arrange
        when(courseService.checkCourseExistById(Mockito.<String>any())).thenReturn(true);
        when(groupRepository.save(Mockito.<Group>any())).thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> groupImpl.createOne(new CreateGroupRequest("42", "Group Name")));
        verify(courseService).checkCourseExistById(eq("42"));
        verify(groupRepository).save(isA(Group.class));
    }

    /**
     * Method under test: {@link GroupImpl#createOne(CreateGroupRequest)}
     */
    @Test
    void testCreateOne3() {
        // Arrange
        when(courseService.checkCourseExistById(Mockito.<String>any())).thenReturn(false);

        // Act
        Group actualCreateOneResult = groupImpl.createOne(new CreateGroupRequest("42", "Group Name"));

        // Assert
        verify(courseService).checkCourseExistById(eq("42"));
        assertNull(actualCreateOneResult);
    }

    /**
     * Method under test: {@link GroupImpl#getById(String)}
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

        // Act
        Group actualById = groupImpl.getById("42");

        // Assert
        verify(groupRepository).findById(eq("42"));
        LocalTime expectedToLocalTimeResult = actualById.getCreateDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualById.getCourse().getUpdatedDate().toLocalTime());
    }

    /**
     * Method under test: {@link GroupImpl#getById(String)}
     */
    @Test
    void testGetById2() {
        // Arrange
        Optional<Group> emptyResult = Optional.empty();
        when(groupRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> groupImpl.getById("42"));
        verify(groupRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link GroupImpl#getById(String)}
     */
    @Test
    void testGetById3() {
        // Arrange
        when(groupRepository.findById(Mockito.<String>any())).thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> groupImpl.getById("42"));
        verify(groupRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link GroupImpl#getAll()}
     */
    @Test
    void testGetAll() {
        // Arrange
        when(groupRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Group> actualAll = groupImpl.getAll();

        // Assert
        verify(groupRepository).findAll();
        assertNull(actualAll);
    }

    /**
     * Method under test: {@link GroupImpl#getAll()}
     */
    @Test
    void testGetAll2() {
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

        ArrayList<Group> groupList = new ArrayList<>();
        groupList.add(group);
        when(groupRepository.findAll()).thenReturn(groupList);

        // Act
        List<Group> actualAll = groupImpl.getAll();

        // Assert
        verify(groupRepository).findAll();
        assertEquals(1, actualAll.size());
        assertSame(groupList, actualAll);
    }

    /**
     * Method under test: {@link GroupImpl#getAll()}
     */
    @Test
    void testGetAll3() {
        // Arrange
        when(groupRepository.findAll()).thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> groupImpl.getAll());
        verify(groupRepository).findAll();
    }

    /**
     * Method under test: {@link GroupImpl#deleteById(String)}
     */
    @Test
    void testDeleteById() {
        // Arrange
        doNothing().when(groupRepository).deleteById(Mockito.<String>any());
        when(groupRepository.existsById(Mockito.<String>any())).thenReturn(true);

        // Act
        groupImpl.deleteById("42");

        // Assert that nothing has changed
        verify(groupRepository).deleteById(eq("42"));
        verify(groupRepository).existsById(eq("42"));
    }

    /**
     * Method under test: {@link GroupImpl#deleteById(String)}
     */
    @Test
    void testDeleteById2() {
        // Arrange
        doThrow(new NoSuchElementException("foo")).when(groupRepository).deleteById(Mockito.<String>any());
        when(groupRepository.existsById(Mockito.<String>any())).thenReturn(true);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> groupImpl.deleteById("42"));
        verify(groupRepository).deleteById(eq("42"));
        verify(groupRepository).existsById(eq("42"));
    }

    /**
     * Method under test: {@link GroupImpl#deleteById(String)}
     */
    @Test
    void testDeleteById3() {
        // Arrange
        when(groupRepository.existsById(Mockito.<String>any())).thenReturn(false);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> groupImpl.deleteById("42"));
        verify(groupRepository).existsById(eq("42"));
    }

    /**
     * Method under test: {@link GroupImpl#addStudentsToGroup(List, String, String)}
     */
    @Test
    void testAddStudentsToGroup() {
        // Arrange, Act and Assert
        assertTrue(
                groupImpl.addStudentsToGroup(new ArrayList<>(), "42", "The characteristics of someone or something").isEmpty());
    }

    /**
     * Method under test: {@link GroupImpl#addStudentsToGroup(List, String, String)}
     */
    @Test
    void testAddStudentsToGroup2() {
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

        User student = new User();
        student.setCourseStudents(new ArrayList<>());
        student.setCourseTeachers(new ArrayList<>());
        student.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        student.setEmail("jane.doe@example.org");
        student.setGroupStudents(new ArrayList<>());
        student.setName("Name");
        student.setPassword("iloveyou");
        student.setRole("Role");
        student.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        student.setUserId("42");
        student.setUsername("janedoe");

        GroupStudent groupStudent = new GroupStudent();
        groupStudent.setDescription("The characteristics of someone or something");
        groupStudent.setGroup(group);
        groupStudent.setGroupId("42");
        groupStudent.setJoinDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        groupStudent.setStudent(student);
        groupStudent.setStudentId("42");
        when(groupStudentRepository.save(Mockito.<GroupStudent>any())).thenReturn(groupStudent);

        ArrayList<String> studentIds = new ArrayList<>();
        studentIds.add("foo");

        // Act
        List<GroupStudent> actualAddStudentsToGroupResult = groupImpl.addStudentsToGroup(studentIds, "42",
                "The characteristics of someone or something");

        // Assert
        verify(groupStudentRepository).save(isA(GroupStudent.class));
        assertEquals(1, actualAddStudentsToGroupResult.size());
    }

    /**
     * Method under test: {@link GroupImpl#addStudentsToGroup(List, String, String)}
     */
    @Test
    void testAddStudentsToGroup3() {
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

        User student = new User();
        student.setCourseStudents(new ArrayList<>());
        student.setCourseTeachers(new ArrayList<>());
        student.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        student.setEmail("jane.doe@example.org");
        student.setGroupStudents(new ArrayList<>());
        student.setName("Name");
        student.setPassword("iloveyou");
        student.setRole("Role");
        student.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        student.setUserId("42");
        student.setUsername("janedoe");

        GroupStudent groupStudent = new GroupStudent();
        groupStudent.setDescription("The characteristics of someone or something");
        groupStudent.setGroup(group);
        groupStudent.setGroupId("42");
        groupStudent.setJoinDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        groupStudent.setStudent(student);
        groupStudent.setStudentId("42");
        when(groupStudentRepository.save(Mockito.<GroupStudent>any())).thenReturn(groupStudent);

        ArrayList<String> studentIds = new ArrayList<>();
        studentIds.add("42");
        studentIds.add("foo");

        // Act
        List<GroupStudent> actualAddStudentsToGroupResult = groupImpl.addStudentsToGroup(studentIds, "42",
                "The characteristics of someone or something");

        // Assert
        verify(groupStudentRepository, atLeast(1)).save(Mockito.<GroupStudent>any());
        assertEquals(2, actualAddStudentsToGroupResult.size());
    }

    /**
     * Method under test: {@link GroupImpl#addStudentsToGroup(List, String, String)}
     */
    @Test
    void testAddStudentsToGroup4() {
        // Arrange
        when(groupStudentRepository.save(Mockito.<GroupStudent>any())).thenThrow(new NoSuchElementException("foo"));

        ArrayList<String> studentIds = new ArrayList<>();
        studentIds.add("foo");

        // Act and Assert
        assertThrows(NoSuchElementException.class,
                () -> groupImpl.addStudentsToGroup(studentIds, "42", "The characteristics of someone or something"));
        verify(groupStudentRepository).save(isA(GroupStudent.class));
    }

    /**
     * Method under test: {@link GroupImpl#getGroupsByCourseId(String)}
     */
    @Test
    void testGetGroupsByCourseId() {
        // Arrange
        ArrayList<Group> groupList = new ArrayList<>();
        when(groupRepository.getAllCourseByCourseId(Mockito.<String>any())).thenReturn(groupList);

        // Act
        List<Group> actualGroupsByCourseId = groupImpl.getGroupsByCourseId("42");

        // Assert
        verify(groupRepository).getAllCourseByCourseId(eq("42"));
        assertTrue(actualGroupsByCourseId.isEmpty());
        assertSame(groupList, actualGroupsByCourseId);
    }

    /**
     * Method under test: {@link GroupImpl#getGroupsByCourseId(String)}
     */
    @Test
    void testGetGroupsByCourseId2() {
        // Arrange
        when(groupRepository.getAllCourseByCourseId(Mockito.<String>any())).thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> groupImpl.getGroupsByCourseId("42"));
        verify(groupRepository).getAllCourseByCourseId(eq("42"));
    }

    /**
     * Method under test: {@link GroupImpl#getUsersInGroup(String)}
     */
    @Test
    void testGetUsersInGroup() {
        // Arrange
        ArrayList<StudentInGroupEntity> studentInGroupEntityList = new ArrayList<>();
        when(groupStudentRepository.getStudentInGroup(Mockito.<String>any())).thenReturn(studentInGroupEntityList);

        // Act
        List<StudentInGroupEntity> actualUsersInGroup = groupImpl.getUsersInGroup("42");

        // Assert
        verify(groupStudentRepository).getStudentInGroup(eq("42"));
        assertTrue(actualUsersInGroup.isEmpty());
        assertSame(studentInGroupEntityList, actualUsersInGroup);
    }

    /**
     * Method under test: {@link GroupImpl#getUsersInGroup(String)}
     */
    @Test
    void testGetUsersInGroup2() {
        // Arrange
        when(groupStudentRepository.getStudentInGroup(Mockito.<String>any())).thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> groupImpl.getUsersInGroup("42"));
        verify(groupStudentRepository).getStudentInGroup(eq("42"));
    }

    /**
     * Method under test: {@link GroupImpl#getStudentNotInGroup(String)}
     */
    @Test
    void testGetStudentNotInGroup() {
        // Arrange
        ArrayList<StudentNotInGroupEntity> studentNotInGroupEntityList = new ArrayList<>();
        when(groupStudentRepository.getStudentNotInGroup(Mockito.<String>any())).thenReturn(studentNotInGroupEntityList);

        // Act
        List<StudentNotInGroupEntity> actualStudentNotInGroup = groupImpl.getStudentNotInGroup("42");

        // Assert
        verify(groupStudentRepository).getStudentNotInGroup(eq("42"));
        assertTrue(actualStudentNotInGroup.isEmpty());
        assertSame(studentNotInGroupEntityList, actualStudentNotInGroup);
    }

    /**
     * Method under test: {@link GroupImpl#getStudentNotInGroup(String)}
     */
    @Test
    void testGetStudentNotInGroup2() {
        // Arrange
        when(groupStudentRepository.getStudentNotInGroup(Mockito.<String>any()))
                .thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> groupImpl.getStudentNotInGroup("42"));
        verify(groupStudentRepository).getStudentNotInGroup(eq("42"));
    }

    /**
     * Method under test: {@link GroupImpl#deleteStudentInGroup(String, List)}
     */
    @Test
    void testDeleteStudentInGroup() {
        // Arrange and Act
        groupImpl.deleteStudentInGroup("42", new ArrayList<>());

        // Assert that nothing has changed
        assertNull(groupImpl.getAll());
    }

    /**
     * Method under test: {@link GroupImpl#deleteStudentInGroup(String, List)}
     */
    @Test
    void testDeleteStudentInGroup2() {
        // Arrange
        doNothing().when(groupStudentRepository).deleteStudentInGroup(Mockito.<String>any(), Mockito.<String>any());

        ArrayList<String> studentIds = new ArrayList<>();
        studentIds.add("foo");

        // Act
        groupImpl.deleteStudentInGroup("42", studentIds);

        // Assert that nothing has changed
        verify(groupStudentRepository).deleteStudentInGroup(eq("foo"), eq("42"));
    }

    /**
     * Method under test: {@link GroupImpl#deleteStudentInGroup(String, List)}
     */
    @Test
    void testDeleteStudentInGroup3() {
        // Arrange
        doNothing().when(groupStudentRepository).deleteStudentInGroup(Mockito.<String>any(), Mockito.<String>any());

        ArrayList<String> studentIds = new ArrayList<>();
        studentIds.add("42");
        studentIds.add("foo");

        // Act
        groupImpl.deleteStudentInGroup("42", studentIds);

        // Assert that nothing has changed
        verify(groupStudentRepository, atLeast(1)).deleteStudentInGroup(Mockito.<String>any(), eq("42"));
    }

    /**
     * Method under test: {@link GroupImpl#deleteStudentInGroup(String, List)}
     */
    @Test
    void testDeleteStudentInGroup4() {
        // Arrange
        doThrow(new NoSuchElementException("foo")).when(groupStudentRepository)
                .deleteStudentInGroup(Mockito.<String>any(), Mockito.<String>any());

        ArrayList<String> studentIds = new ArrayList<>();
        studentIds.add("foo");

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> groupImpl.deleteStudentInGroup("42", studentIds));
        verify(groupStudentRepository).deleteStudentInGroup(eq("foo"), eq("42"));
    }

    /**
     * Method under test: {@link GroupImpl#updateGroup(String, UpdateGroupRequest)}
     */
    @Test
    void testUpdateGroup() {
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

        Group group2 = new Group();
        group2.setCourse(course2);
        group2.setCourseId("42");
        group2.setCreateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group2.setGroupId("42");
        group2.setGroupName("Group Name");
        group2.setGroupStudents(new ArrayList<>());
        group2.setUpdateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group2.setViewPermissionMaterials(new ArrayList<>());
        group2.setViewPermissionTopics(new ArrayList<>());
        when(groupRepository.save(Mockito.<Group>any())).thenReturn(group2);
        when(groupRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Group actualUpdateGroupResult = groupImpl.updateGroup("42", new UpdateGroupRequest("Group Name"));

        // Assert
        verify(groupRepository).findById(eq("42"));
        verify(groupRepository).save(isA(Group.class));
        LocalTime expectedToLocalTimeResult = actualUpdateGroupResult.getCreateDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualUpdateGroupResult.getCourse().getUpdatedDate().toLocalTime());
    }

    /**
     * Method under test: {@link GroupImpl#updateGroup(String, UpdateGroupRequest)}
     */
    @Test
    void testUpdateGroup2() {
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
        when(groupRepository.save(Mockito.<Group>any())).thenThrow(new NoSuchElementException("foo"));
        when(groupRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> groupImpl.updateGroup("42", new UpdateGroupRequest("Group Name")));
        verify(groupRepository).findById(eq("42"));
        verify(groupRepository).save(isA(Group.class));
    }

    /**
     * Method under test: {@link GroupImpl#updateGroup(String, UpdateGroupRequest)}
     */
    @Test
    void testUpdateGroup3() {
        // Arrange
        Optional<Group> emptyResult = Optional.empty();
        when(groupRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> groupImpl.updateGroup("42", new UpdateGroupRequest("Group Name")));
        verify(groupRepository).findById(eq("42"));
    }
}
