package com.example.codeE.model.group;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    // groupId, groupName, courseId, dateCreate, description, numberMember   
    //primary key
    @NonNull
    private String groupId;
    //foreign key
    @NonNull
    private String courseId;
    @NonNull
    private String groupName;
    @NonNull
    private Date dateCreate;
    @NonNull
    private String description;
    private int numberMember;
}
