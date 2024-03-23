package com.example.codeE.service.topic;

import com.example.codeE.model.group.Group;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.request.topic.CreateTopicRequest;
import com.example.codeE.request.topic.TopicGetResponse;
import com.example.codeE.request.topic.UpdateTopicRequest;
import com.example.codeE.service.common.CommonService;

import java.util.List;

public interface TopicService extends CommonService<Topic, CreateTopicRequest>{
    List<TopicGetResponse> getAllTopicsByCourseId(String courseId);

    Topic updateTopic(UpdateTopicRequest topicRequest);

    List<Group> getAllGroupsByTopicId(String topicId);

    boolean removeViewPermission(String topicId, List<String> groupIds);

    boolean addViewPermission(String topicId, List<String> groupIds);

    List<TopicGetResponse> getTopicByUserId(String studentId, String courseId);
}
