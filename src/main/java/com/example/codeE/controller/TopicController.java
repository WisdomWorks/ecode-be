package com.example.codeE.controller;

import com.example.codeE.model.topic.Topic;
import com.example.codeE.request.topic.CreatePermissionTopicRequest;
import com.example.codeE.request.topic.CreateTopicRequest;
import com.example.codeE.request.topic.UpdateTopicRequest;
import com.example.codeE.service.topic.TopicService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/topics")
@Validated
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTopicsByCourseId(@RequestParam String courseId) {
        return ResponseEntity.ok(this.topicService.getAllTopicsByCourseId(courseId));
    }

    @PostMapping
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createTopic(@Valid @RequestBody CreateTopicRequest topicRequest) {
        Topic topic = topicService.createOne(topicRequest);
        System.out.println(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topic);
    }

    @PutMapping
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTopic(@Valid @RequestBody UpdateTopicRequest topic) {
        Topic updatedTopic = this.topicService.updateTopic(topic);
        return ResponseEntity.ok(updatedTopic);
    }

    @DeleteMapping
    @RequestMapping(value = "/{topicId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTopic(@PathVariable String topicId) {
        this.topicService.deleteById(topicId);
        return ResponseEntity.ok("Topic deleted successfully");
    }

    @GetMapping
    @RequestMapping(value = "/{topicId}", method = RequestMethod.GET)
    public ResponseEntity<?> getTopic(@PathVariable String topicId) {
        return ResponseEntity.ok(this.topicService.getById(topicId));
    }

    @GetMapping
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ResponseEntity<?> getPublicGroups(@RequestParam String topicId) {
        return ResponseEntity.ok(this.topicService.getAllGroupsByTopicId(topicId));
    }

    @PostMapping
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ResponseEntity<?> addViewPermission(@RequestBody CreatePermissionTopicRequest request) {
        if (this.topicService.addViewPermission(request.getTopicId(), request.getGroupIds(), request.isShowAll()))
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","add permission success"));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message","Something wrong when add view permission"));
    }

    @DeleteMapping
    @RequestMapping(value = "/view", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeViewPermission(@RequestParam String topicId, @RequestParam List<String> groupIds) {
        if (this.topicService.removeViewPermission(topicId, groupIds))
            return ResponseEntity.status(HttpStatus.OK).body("Delete success");
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something wrong when delete view permission");
    }

    @GetMapping
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getTopicByUserId(@PathVariable String userId, @RequestParam String courseId) {
        return ResponseEntity.ok(this.topicService.getTopicByUserId(userId, courseId));
    }
}
