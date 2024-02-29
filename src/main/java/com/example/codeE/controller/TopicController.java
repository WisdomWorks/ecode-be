package com.example.codeE.controller;

import com.example.codeE.model.topic.Topic;
import com.example.codeE.service.topic.TopicImpl;
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
    private TopicImpl topicImplement;

    @GetMapping
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity<?> getAllTopicsByCourseId(@RequestParam String courseId) {
        return ResponseEntity.ok(this.topicImplement.getAllTopicsByCourseId(courseId));
    }

    @PostMapping
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity<?> createTopic(@RequestBody Topic topic){
        Topic createdTopic = this.topicImplement.createTopic(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTopic);
    }

    @PutMapping
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public ResponseEntity<?> updateTopic(@RequestBody Topic topic){
        Topic updatedTopic = this.topicImplement.updateTopic(topic);
        return ResponseEntity.ok(updatedTopic);
    }

    @DeleteMapping
    @RequestMapping(value = "/{topicId}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTopic(@PathVariable String topicId){
        this.topicImplement.deleteTopic(topicId);
        return ResponseEntity.ok("Topic deleted successfully");
    }

    @GetMapping
    @RequestMapping(value = "/{topicId}",method = RequestMethod.GET)
    public ResponseEntity<?> getTopic(@PathVariable String topicId) {
        return ResponseEntity.ok(this.topicImplement.getTopic(topicId));
    }
}
