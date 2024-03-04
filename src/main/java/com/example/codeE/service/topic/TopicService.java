package com.example.codeE.service.topic;

import com.example.codeE.model.topic.Topic;
import com.example.codeE.request.topic.CreateTopicRequest;
import com.example.codeE.request.topic.UpdateTopicRequest;
import com.example.codeE.service.common.CommonService;

import java.util.List;

public interface TopicService extends CommonService<Topic, CreateTopicRequest>{
    List<Topic> getAllTopicsByCourseId(String courseId);

    Topic updateTopic(UpdateTopicRequest topicRequest);



}
