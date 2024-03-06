package com.example.codeE.service.group;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

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
    public List<Group> getGroupsByCourseId(String courseId) {
        return this.groupRepository.getAllCourseByCourseId(courseId);
    }

    @Override
    public List<User> getUsersInGroup(String groupId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsersInGroup'");
    }


    @Override
    public Boolean updateGroupById(String groupId, Group updatedGroup) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateGroupById'");
    }

    
}
