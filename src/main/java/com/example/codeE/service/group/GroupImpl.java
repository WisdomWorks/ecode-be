package com.example.codeE.service.group;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import com.example.codeE.entity.group.StudentInGroupEntity;
import com.example.codeE.entity.group.StudentNotInGroupEntity;
import com.example.codeE.model.group.GroupStudent;
import com.example.codeE.model.group.UpdateGroupRequest;
import com.example.codeE.repository.GroupStudentRepository;
import com.example.codeE.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.codeE.model.group.Group;
import com.example.codeE.repository.GroupRepository;
import com.example.codeE.request.group.CreateGroupRequest;

import org.springframework.stereotype.Service;

@Service
public class GroupImpl implements GroupService{

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private GroupStudentRepository groupStudentRepository;

    @Override
    public Group createOne(CreateGroupRequest groupRequest) {
        if (this.courseService.checkCourseExistById(groupRequest.getCourseId())){
            var group = new Group();
            group.setGroupId(UUID.randomUUID().toString());
            group.setGroupName(groupRequest.getGroupName());
            group.setCourseId(groupRequest.getCourseId());
            return this.groupRepository.save(group);
        }else return null;
    }

    @Override
    public Group getById(String GroupId) {
        return this.groupRepository.findById(GroupId).orElseThrow(() -> new NoSuchElementException("No group found with ID:" + GroupId));
    }

    @Override
    public List<Group> getAll() {
        var result = this.groupRepository.findAll();
        if(result.size() > 0) {
            return result;
        }else 
            return null;
    }

    @Override
    public void deleteById(String id) {
        if (groupRepository.existsById(id)) {
            this.groupRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Group not found with id " + id);
        }
    }

    @Override
    public List<GroupStudent> addStudentsToGroup(List<String> studentIds, String groupId, String description) {
        var result = new ArrayList<GroupStudent>();
        System.out.println(studentIds);
        System.out.println(groupId);
        for (String studentId: studentIds
             ) {
            var groupStudent = this.groupStudentRepository.save(new GroupStudent(studentId, groupId, description));
            result.add(groupStudent);
        }
        return result;
    }
    
    @Override
    public List<Group> getGroupsByCourseId(String courseId) {
        return this.groupRepository.getAllCourseByCourseId(courseId);
    }

    @Override
    public List<StudentInGroupEntity> getUsersInGroup(String groupId) {
        return this.groupStudentRepository.getStudentInGroup(groupId);
    }
    @Override
    public List<StudentNotInGroupEntity> getStudentNotInGroup(String groupId) {
        return this.groupStudentRepository.getStudentNotInGroup(groupId);
    }

    @Override
    public void deleteStudentInGroup(String groupId, List<String> studentIds) {
        for(String studentId: studentIds){
            this.groupStudentRepository.deleteStudentInGroup(studentId, groupId);
        }
    }
    @Override
    public Group updateGroup(String groupId, UpdateGroupRequest updateGroup){
        var group = this.groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("Group not found"));
        group.setGroupName(updateGroup.getGroupName());
        group.setUpdateDate(LocalDateTime.now());
        return this.groupRepository.save(group);
    }
}
