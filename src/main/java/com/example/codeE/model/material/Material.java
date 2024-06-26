package com.example.codeE.model.material;

import com.example.codeE.constant.Constant;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.request.material.CreateMaterialRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "material")
public class Material {

    @Id
    @Column(name = "material_id")
    @NotBlank(message = "Material ID is required")
    private String materialId;

    @Column(name = "material_name")
    @NotBlank(message = "Material name is required")
    private String materialName;

    @Column(name = "material_type", nullable = false)
    @NotBlank(message = "Material type is required")
    @Pattern(regexp = "^(file|string)$", message = "Invalid material type. Allowed types are file and link.")
    private String materialType;

    @Column(name = "topic_id", length = 36, nullable = false)
    @NotBlank(message = "Topic ID is required")
    @Size(max = 36, message = "TopicID is GUID type")
    private String topicId;

    @Column(name = "storage_url")
    private String storageUrl;

    @Column(name = "is_show_all")
    private boolean isShowAll;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "created_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime createdDate;

    @Column(name = "updated_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime updatedDate;

    @JsonIgnore
    @ManyToOne(optional=false)
    @JoinColumn(name = "topic_id", insertable=false, updatable=false)
    private Topic topic;

    @JsonIgnore
    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
    private List<ViewPermissionMaterial> viewPermissionMaterials;

    public Material(String materialId, CreateMaterialRequest request) {
        this.materialId = materialId;
        this.materialName = request.getMaterialName();
        this.materialType = request.getMaterialType();
        this.topicId = request.getTopicId();
        this.storageUrl = request.getUrl();
        this.description = request.getDescription();
    }

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdDate = now;
        this.updatedDate = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Material{" +
                "materialId='" + materialId + '\'' +
                ", materialName='" + materialName + '\'' +
                ", materialType='" + materialType + '\'' +
                ", topicId='" + topicId + '\'' +
                ", storageUrl='" + storageUrl + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}