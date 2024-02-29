package com.example.codeE.service.topic;

import com.example.codeE.model.topic.Topic;

import java.util.List;

public interface TopicService {
    List<Topic> getAllTopicsByCourseId(String courseId);

    Topic createTopic(Topic topic);

    Topic updateTopic(Topic topic);

    void deleteTopic(String topicId);

    Topic getTopic(String topicId);


}
