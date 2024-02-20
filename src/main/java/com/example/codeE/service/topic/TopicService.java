package com.example.codeE.service.topic;

import com.example.codeE.model.topic.Topic;

import java.util.List;

public interface TopicService {
    List<Topic> getAllTopicsByCourseId(String courseId);
}
