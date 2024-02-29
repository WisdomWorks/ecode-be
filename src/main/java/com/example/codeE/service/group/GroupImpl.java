package com.example.codeE.service.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.codeE.model.group.Group;
import com.example.codeE.repository.GroupRepository;

public class GroupImpl implements GroupService{

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group getGroupById(String id) {
        return groupRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean createGroup(Group group) {
        groupRepository.save(group);
        return true;
    }

    @Override
    public Group updateGroup(String id, Group group) {
        // Group existingGroup = groupRepository.findById(id).orElse(null);
        // if(existingGroup != null) {
        //     existingGroup.setGroupName(group.getGroupName());
        //     existingGroup.setNumberMember(group.getNumberMember());
        //     groupRepository.save(existingGroup);
        // }
        return new Group();
    }

    @Override
    public void deleteGroup(String id) {
        groupRepository.deleteById(id);
    }
    
}
