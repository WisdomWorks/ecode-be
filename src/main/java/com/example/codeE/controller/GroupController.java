package com.example.codeE.controller;

import com.example.codeE.request.group.CreateGroupRequest;

import java.util.List;
import java.util.Map;

import com.example.codeE.request.group.CreateGroupStudentRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
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
    @RequestMapping(value = "{groupId}",method = RequestMethod.GET)
    public ResponseEntity<?> getGroupById(@PathVariable String groupId) {
        var result = this.groupService.getById(groupId);
        try{
            if(result != null){
                return ResponseEntity.status(HttpStatus.OK).body(result);
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not find group with id: " + groupId);
            }
        } catch(Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    @PostMapping
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createGroup(@RequestBody CreateGroupRequest group) {
        return new ResponseEntity<>(groupService.createOne(group), HttpStatus.CREATED);
    }

    @DeleteMapping
    @RequestMapping(value = "{groupId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteGroup(@Valid @PathVariable String groupId) {
        this.groupService.deleteById(groupId);
        return ResponseEntity.ok(Map.of("message" , "Delete group successfully"));
    }

    @GetMapping
    @RequestMapping(value = "course/{courseId}", method = RequestMethod.GET)
    public ResponseEntity<?> getGroupsByCourseId(@PathVariable String courseId){
        return ResponseEntity.status(HttpStatus.OK).body(this.groupService.getGroupsByCourseId(courseId));
    }
    @PostMapping
    @RequestMapping(value = "/student" , method = RequestMethod.POST)
    public ResponseEntity<?> addStudentsToGroup(@RequestBody CreateGroupStudentRequest createRequest){
        var result = this.groupService.addStudentsToGroup(createRequest.getStudentIds(), createRequest.getGroupId(), createRequest.getDescription());
        if(!result.isEmpty()){
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not add student to group! ");
    }

    @GetMapping
    @RequestMapping(value = "{groupId}/student/in-group", method = RequestMethod.GET)
    public ResponseEntity<?> getStudentInGroup(@PathVariable String groupId){
        return new ResponseEntity<>(this.groupService.getUsersInGroup(groupId), HttpStatus.CREATED);
    }

    @GetMapping
    @RequestMapping(value = "{groupId}/student/not-in-group", method = RequestMethod.GET)
    public ResponseEntity<?> getStudentNotInGroup(@PathVariable String groupId){
        return ResponseEntity.status(HttpStatus.OK).body(this.groupService.getStudentNotInGroup(groupId));
    }

    @DeleteMapping
    @RequestMapping(value = "{groupId}/student", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStudentInGroup(@PathVariable String groupId, @RequestBody List<String> studentIds){
        this.groupService.deleteStudentInGroup(groupId, studentIds);
        try{
            return ResponseEntity.status(HttpStatus.OK).body("Delete students successful");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }

}
