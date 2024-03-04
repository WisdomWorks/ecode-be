package com.example.codeE.service.topic;

import com.example.codeE.model.topic.Topic;
import com.example.codeE.repository.TopicRepository;
import com.example.codeE.request.topic.CreateTopicRequest;
import com.example.codeE.request.topic.UpdateTopicRequest;
import com.example.codeE.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TopicImpl implements TopicService{
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CourseService courseService;
    @Override
    public List<Topic> getAllTopicsByCourseId(String courseId) {
        return this.topicRepository.getAllTopicsByCourseId(courseId);
    }

    @Override
    public Topic updateTopic(UpdateTopicRequest topicRequest) {
        if (this.topicRepository.existsById(topicRequest.getTopicId())) {
            Topic topic = this.topicRepository.findById(topicRequest.getTopicId()).get();
            topic.setTopicName(topicRequest.getTopicName());
            topic.setDescription(topicRequest.getDescription());
            return this.topicRepository.save(topic);
        }
        return null;
    }

    @Override
    public Topic createOne(CreateTopicRequest topicRequest) {
        String courseId = topicRequest.getCourseId();
        System.out.println(courseId);
        if (this.courseService.checkCourseExistById(courseId)) {
            Topic topic = new Topic();
            topic.setTopicId(UUID.randomUUID().toString());
            topic.setCourseId(courseId);
            topic.setTopicName(topicRequest.getTopicName());
            topic.setDescription(topicRequest.getDescription());
            return this.topicRepository.save(topic);
        }
        return null;
    }

    @Override
    public Topic getById(String id) {
        return this.topicRepository.findById(id).orElse(null);
    }

    @Override
    public List<Topic> getAll() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean deleteById(String id) {
        if (!topicRepository.existsById(id)) {
            return false;
        }
        this.topicRepository.deleteById(id);
        return true;
    }
}
