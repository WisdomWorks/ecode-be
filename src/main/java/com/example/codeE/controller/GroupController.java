package com.example.codeE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import com.example.codeE.model.group.Group;
import com.example.codeE.service.group.GroupService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
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
        return new ResponseEntity<>(groupService.getById(groupId), HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createGroup(@RequestBody Group group) {
        return new ResponseEntity<>(groupService.createOne(group), HttpStatus.CREATED);
    }

    // @PutMapping("/{groupId}")
    // public ResponseEntity<?> updateGroup(String groupId, @RequestBody Group group) {
    //     return new ResponseEntity<>(groupService.updateGroupById(groupId, group), HttpStatus.OK);
    // }
    // @DeleteMapping("/{groupId}")
    // public ResponseEntity<?> deleteGroup(String groupId) {
    //     groupService.deleteById(groupId);
    //     return new ResponseEntity<>(HttpStatus.OK);
    // }
}
