package com.example.codeE.controller;

import com.example.codeE.request.group.CreateGroupRequest;

import java.util.Map;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import com.example.codeE.service.group.GroupService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/groups")
@Validated
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity<?> getAllGroups() {
        return new ResponseEntity<>(groupService.getAll(), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping(value = "{groupId}",method = RequestMethod.GET)
    public ResponseEntity<?> getGroupById(@PathVariable String groupId) {
        var result = this.groupService.getById(groupId);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }       
        return ResponseEntity.status(HttpStatus.CREATED).body("Can not find group with id: " + groupId);
    }

    @PostMapping
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createGroup(@RequestBody CreateGroupRequest group) {
        return new ResponseEntity<>(groupService.createOne(group), HttpStatus.CREATED);
    }

    // @PutMapping("/{groupId}")
    // public ResponseEntity<?> updateGroup(String groupId, @RequestBody Group group) {
    //     return new ResponseEntity<>(groupService.updateGroupById(groupId, group), HttpStatus.OK);
    // }
    @DeleteMapping
    @RequestMapping(value = "{groupId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteGroup(@Valid String groupId) {
        this.groupService.deleteById(groupId);
        return ResponseEntity.ok(Map.of("message" , "Delete group successfully"));
    }

    @GetMapping
    @RequestMapping(value = "/get-groups-in-course/{courseId}", method = RequestMethod.GET)
    public  ResponseEntity<?> getGroupsByCourseId(@PathVariable String courseId){
        return ResponseEntity.status(HttpStatus.OK).body(this.groupService.getGroupsByCourseId(courseId));
    }
}
