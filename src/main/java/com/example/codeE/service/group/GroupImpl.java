package com.example.codeE.service.group;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.codeE.model.group.Group;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupImpl implements GroupService{

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group createOne(Group group) {      
        group.setGroupId(UUID.randomUUID().toString());
        return this.groupRepository.save(group);
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
    public List<Group> getGroupsByCourseId(String courseId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGroupsByCourseId'");
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
