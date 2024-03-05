package com.example.codeE.service.group;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.codeE.entity.GetGroupStudentEntity;
import com.example.codeE.model.group.GroupStudent;
import com.example.codeE.repository.GroupStudentRepository;
import com.example.codeE.request.user.GetUsersRequest;
import com.example.codeE.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.codeE.model.group.Group;
import com.example.codeE.model.user.User;
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
       Optional<Group> groupOptional = this.groupRepository.findById(GroupId);
        return groupOptional.orElse(null);
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
    public boolean deleteById(String id) {
        if (!groupRepository.existsById(id)) {
            return false;
        }
        this.groupRepository.deleteById(id);
        return true;
    }

    @Override
    public List<GroupStudent> addStudentsToGroup(List<String> studentIds, String groupId, String description) {
        var result = new ArrayList<GroupStudent>();
        //need to check exist or not ?
        studentIds.forEach(studentId -> {
            var newStudentInGroup = new GroupStudent(studentId, groupId, description);
            result.add(this.groupStudentRepository.save(newStudentInGroup));
        });
        return result;
    }
    
    @Override
    public List<Group> getGroupsByCourseId(String courseId) {
        return this.groupRepository.getAllCourseByCourseId(courseId);
    }

    @Override
    public List<GetGroupStudentEntity> getUsersInGroup(String groupId) {
        return this.groupStudentRepository.getStudentInGroup(groupId);
    }

    @Override
    public List<User> getStudentNotInGroup(String groupId) {
        return null;
    }

//    @Override
//    public Boolean updateGroupById(String groupId, Group updatedGroup) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'updateGroupById'");
//    }
}
