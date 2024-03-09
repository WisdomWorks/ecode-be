package com.example.codeE.service.group;

import java.util.List;

import com.example.codeE.entity.GetGroupStudentEntity;
import com.example.codeE.model.group.Group;
import com.example.codeE.model.group.GroupStudent;
import com.example.codeE.model.user.User;
import com.example.codeE.request.group.CreateGroupRequest;
import com.example.codeE.request.user.GetUsersRequest;
import com.example.codeE.service.common.CommonService;

public interface GroupService extends CommonService<Group, CreateGroupRequest> {
//    Boolean updateGroupById(String groupId, Group updatedGroup);
    List<GroupStudent> addStudentsToGroup(List<String> studentIds, String groupId, String description);
    List<Group> getGroupsByCourseId(String courseId);
    List<GetGroupStudentEntity> getUsersInGroup(String groupId);
    List<User> getStudentNotInGroup(String groupId);
}
