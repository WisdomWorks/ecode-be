package com.example.codeE.service.topic;

import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.group.Group;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.repository.CourseRepository;
import com.example.codeE.repository.GroupRepository;
import com.example.codeE.repository.TopicRepository;
import com.example.codeE.repository.ViewPermissionTopicRepository;
import com.example.codeE.request.group.GroupTopicResponse;
import com.example.codeE.request.topic.CreateTopicRequest;
import com.example.codeE.request.topic.TopicGetResponse;
import com.example.codeE.request.topic.UpdateTopicRequest;
import com.example.codeE.service.material.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TopicImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private ViewPermissionTopicRepository viewPermissionTopicRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private MaterialService materialService;

    @Override
    public List<TopicGetResponse> getAllTopicsByCourseId(String courseId) {
        this.courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("No course found with ID: " + courseId));
        var topics = this.topicRepository.getAllTopicsByCourseId(courseId);
        List<TopicGetResponse> result = new ArrayList<>();
        for (var item : topics) {
            var materials = this.materialService.getAllByTopicId(item.getTopicId());
            List<Exercise> exercises = new ArrayList<Exercise>();
            List<GroupTopicResponse> groupsResponse = new ArrayList<>();
            var groups = this.viewPermissionTopicRepository.getAllGroupsByTopicId(item.getTopicId());
            for(var g: groups){
                var group = this.groupRepository.findById(g.getGroupId()).get();
                var groupItem = new GroupTopicResponse();
                groupItem.setGroupName(group.getGroupName());
                groupItem.setGroupId(group.getGroupId());
                groupsResponse.add(groupItem);
            }
            result.add(new TopicGetResponse(item, materials, exercises, groupsResponse));
        }
        return result;
    }

    @Override
    public Topic updateTopic(UpdateTopicRequest topicRequest) {
        this.topicRepository.findById(topicRequest.getTopicId()).orElseThrow(() -> new NoSuchElementException("No topic found with ID: " + topicRequest.getTopicId()));
        try {
            Topic topic = this.topicRepository.findById(topicRequest.getTopicId()).get();
            topic.setTopicName(topicRequest.getTopicName());
            topic.setDescription(topicRequest.getDescription());
            return this.topicRepository.save(topic);
        } catch (Exception e) {
            LoggerHelper.logError(e.getMessage());
            throw new RuntimeException("Can not update topic");
        }
    }

    @Override
    public List<Group> getAllGroupsByTopicId(String topicId) {
        this.topicRepository.findById(topicId).orElseThrow(() -> new NoSuchElementException("No topic found with ID: " + topicId));
        List<Group> result = new ArrayList<Group>();
        var permission = this.viewPermissionTopicRepository.getAllGroupsByTopicId(topicId);
        for (var p : permission) {
            var group = this.groupRepository.findById(p.getGroupId()).get();
            result.add(group);
        }
        return result;
    }

    @Override
    public boolean removeViewPermission(String topicId, List<String> groupIds) {
        this.topicRepository.findById(topicId).orElseThrow(() -> new NoSuchElementException("No topic found with ID: " + topicId));
        for (String groupId : groupIds)
            this.groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("No group found with ID: " + groupId));
        try {
            for (String groupId : groupIds) {
                this.viewPermissionTopicRepository.removeViewPermission(topicId, groupId);
            }

        } catch (Exception e) {
            LoggerHelper.logError(e.getMessage());
            throw new RuntimeException("Something wrong when delete view permission");
        }
        return true;
    }

    @Override
    public boolean addViewPermission(String topicId, List<String> groupIds) {
        this.topicRepository.findById(topicId).orElseThrow(() -> new NoSuchElementException("No group found with ID: " + topicId));
        for (String groupId : groupIds)
            this.groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("No group found with ID: " + groupId));
        try {
            this.viewPermissionTopicRepository.removeViewPermissionByTopicId(topicId);
            for (String groupId : groupIds) {
                this.viewPermissionTopicRepository.addViewPermission(topicId, groupId);
            }
        } catch (Exception e) {
            LoggerHelper.logError(e.getMessage());
            throw new RuntimeException("Something wrong when delete view permission");
        }
        return true;
    }

    @Override
    public List<TopicGetResponse> getTopicByUserId(String studentId, String courseId) {

        var response = new ArrayList<TopicGetResponse>();
        var topics = this.topicRepository.getTopicByUser(studentId, courseId);
        for (var item : topics) {
            var materials = this.materialService.getMaterialByUserId(studentId, item.getTopicId());
            var exercises = new ArrayList<Exercise>();
            List<GroupTopicResponse> groupsResponse = new ArrayList<>();
            var groups = this.viewPermissionTopicRepository.getAllGroupsByTopicId(item.getTopicId());
            for(var g: groups){
                var group = this.groupRepository.findById(g.getGroupId()).get();
                var groupItem = new GroupTopicResponse();
                groupItem.setGroupName(group.getGroupName());
                groupItem.setGroupId(group.getGroupId());
                groupsResponse.add(groupItem);
            }
            response.add(new TopicGetResponse(item, materials, exercises, groupsResponse));
        }
        return response;
    }

    @Override
    public Topic createOne(CreateTopicRequest topicRequest) {
        this.courseRepository.findById(topicRequest.getCourseId()).orElseThrow(() -> new NoSuchElementException("No course found with ID: " + topicRequest.getCourseId()));
        try {
            Topic topic = new Topic();
            topic.setTopicId(UUID.randomUUID().toString());
            topic.setCourseId(topicRequest.getCourseId());
            topic.setTopicName(topicRequest.getTopicName());
            topic.setDescription(topicRequest.getDescription());
            return this.topicRepository.save(topic);
        } catch (Exception e) {
            LoggerHelper.logError(e.getMessage());
            throw new RuntimeException("Can not create new topic");
        }
    }

    @Override
    public Topic getById(String id) {
        return this.topicRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No group found with ID: " + id));
    }

    @Override
    public List<Topic> getAll() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteById(String id) {
        if (topicRepository.existsById(id)) {
            this.topicRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Topic not found with id: " + id);
        }
    }
}
