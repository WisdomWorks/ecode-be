package com.example.codeE.model.material;

import com.example.codeE.request.material.CreateMaterialRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;


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

    @Column(name = "material_type", nullable = false)
    @NotBlank(message = "Material type is required")
    @Pattern(regexp = "^(file|folder)$", message = "Invalid material type. Allowed types are file and folder.")
    private String materialType;

    @Column(name = "topic_id", length = 36, nullable = false)
    @NotBlank(message = "Topic ID is required")
    @Size(max = 36, message = "TopicID is GUID type")
    private String topicId;

    @Column(name = "storage_url")
    private String storageUrl;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date", nullable = false)
    private LocalDateTime updatedDate;

    public Material(String materialId, CreateMaterialRequest request) {
        this.materialId = materialId;
        this.materialType = request.getMaterialType();
        this.topicId = request.getTopicId();
        this.storageUrl = request.getStorageUrl();
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
}