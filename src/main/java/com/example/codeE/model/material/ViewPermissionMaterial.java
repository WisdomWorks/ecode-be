package com.example.codeE.model.material;

import com.example.codeE.model.group.Group;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.model.topic.ViewPermissionTopic;
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
@Entity(name = "view_permission_material")
@IdClass(ViewPermissionMaterialId.class)
public class ViewPermissionMaterial {
    @Id
    @NotBlank(message = "Material ID is required")
    @Column(name = "material_id")
    private String materialId;

    @Id
    @NotBlank(message = "Group ID is required")
    @Column(name = "group_id")
    private String groupId;

    @JsonIgnore
    @ManyToOne(optional=false)
    @JoinColumn(name = "material_id", insertable=false, updatable=false)
    private Material material;

    @JsonIgnore
    @ManyToOne(optional=false)
    @JoinColumn(name = "group_id", insertable=false, updatable=false)
    private Group group;
}
