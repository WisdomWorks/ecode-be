package com.example.codeE.service.topic;

import com.example.codeE.model.group.Group;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.repository.GroupRepository;
import com.example.codeE.repository.TopicRepository;
import com.example.codeE.request.topic.CreateTopicRequest;
import com.example.codeE.request.topic.UpdateTopicRequest;
import com.example.codeE.service.course.CourseService;
import com.example.codeE.service.group.GroupService;
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

    @Autowired
    private GroupRepository groupRepository;
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
    public List<Group> getAllGroupsByTopicId(String topicId) {
        if (this.topicRepository.existsById(topicId)) {
            return this.topicRepository.getAllGroupsByTopicId(topicId);
        }
        return null;
    }

    @Override
    public boolean removeViewPermission(String topicId, List<String> groupIds) {
        if (!this.topicRepository.existsById(topicId)) {
            return false;
        }
        for (String groupId : groupIds) {
            if (!this.groupRepository.existsById(groupId)) {
                return false;
            }
            this.topicRepository.removeViewPermission(topicId, groupId);
    }
        return true;
    }

    @Override
    public boolean addViewPermission(String topicId, List<String> groupIds) {
        if (!this.topicRepository.existsById(topicId)) {
            return false;
        }
        for (String groupId : groupIds) {
            if (!this.groupRepository.existsById(groupId)) {
                return false;
            }
            System.out.println(groupId);
            this.topicRepository.addViewPermission(topicId, groupId);
        }
        return true;
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
