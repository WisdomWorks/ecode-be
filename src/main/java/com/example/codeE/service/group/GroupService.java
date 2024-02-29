package com.example.codeE.service.group;

import java.util.List;

import com.example.codeE.model.group.Group;

public interface GroupService {
    // crud 
    List<Group> getAllGroups();
    Group getGroupById(String id);
    Boolean createGroup(Group group);
    Group updateGroup(String id, Group group);
    void deleteGroup(String id); 
}
