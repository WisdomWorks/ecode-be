package com.example.codeE.model.topic;

import com.example.codeE.model.group.Group;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "view_permission_topic")
@IdClass(ViewPermissionTopic.class)
public class ViewPermissionTopic {
    @Id
    @NotBlank(message = "Topic ID is required")
    @Column(name = "topic_id")
    private String topicId;
    @Id
    @NotBlank(message = "Group ID is required")
    @Column(name = "group_id")
    private String groupId;

    @JsonIgnore
    @ManyToOne(optional=false)
    @JoinColumn(name = "topic_id", insertable=false, updatable=false)
    private Topic topic;

    @JsonIgnore
    @ManyToOne(optional=false)
    @JoinColumn(name = "group_id", insertable=false, updatable=false)
    private Group group;
}
