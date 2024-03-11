package com.example.codeE.service.group;

import java.util.List;

import com.example.codeE.entity.group.StudentInGroupEntity;
import com.example.codeE.entity.group.StudentNotInGroupEntity;
import com.example.codeE.model.group.Group;
import com.example.codeE.model.group.GroupStudent;
import com.example.codeE.request.group.CreateGroupRequest;
import com.example.codeE.service.common.CommonService;

public interface GroupService extends CommonService<Group, CreateGroupRequest> {
//    Boolean updateGroupById(String groupId, Group updatedGroup);
    List<GroupStudent> addStudentsToGroup(List<String> studentIds, String groupId, String description);
    List<Group> getGroupsByCourseId(String courseId);
    List<StudentInGroupEntity> getUsersInGroup(String groupId);
    List<StudentNotInGroupEntity> getStudentNotInGroup(String groupId);
    void deleteStudentInGroup(String groupId, List<String> studentIds);
}
