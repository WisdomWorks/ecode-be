package com.example.codeE.controller;

import com.example.codeE.model.topic.Topic;
import com.example.codeE.request.topic.CreateTopicRequest;
import com.example.codeE.request.topic.UpdateTopicRequest;
import com.example.codeE.service.topic.TopicImpl;
import com.example.codeE.service.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
@Validated
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity<?> getAllTopicsByCourseId(@RequestParam String courseId) {
        return ResponseEntity.ok(this.topicService.getAllTopicsByCourseId(courseId));
    }

    @PostMapping
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity<?> createTopic(@RequestBody CreateTopicRequest topicRequest){
        Topic topic = topicService.createOne(topicRequest);
        System.out.println(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topic);
    }

    @PutMapping
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public ResponseEntity<?> updateTopic(@RequestBody UpdateTopicRequest topic){
        Topic updatedTopic = this.topicService.updateTopic(topic);
        return ResponseEntity.ok(updatedTopic);
    }

    @DeleteMapping
    @RequestMapping(value = "/{topicId}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTopic(@PathVariable String topicId){
        this.topicService.deleteById(topicId);
        return ResponseEntity.ok("Topic deleted successfully");
    }

    @GetMapping
    @RequestMapping(value = "/{topicId}",method = RequestMethod.GET)
    public ResponseEntity<?> getTopic(@PathVariable String topicId) {
        return ResponseEntity.ok(this.topicService.getById(topicId));
    }
}
