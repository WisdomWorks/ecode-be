package com.example.codeE.service.topic;

import com.example.codeE.model.topic.Topic;
import com.example.codeE.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicImpl implements TopicService{
    @Autowired
    private TopicRepository topicRepository;
    @Override
    public List<Topic> getAllTopicsByCourseId(String courseId) {
        return this.topicRepository.getAllTopicsByCourseId(courseId);
    }
}
