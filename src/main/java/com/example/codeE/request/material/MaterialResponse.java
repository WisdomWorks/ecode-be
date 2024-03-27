package com.example.codeE.request.material;

import com.example.codeE.constant.Constant;
import com.example.codeE.model.material.Material;
import com.example.codeE.request.group.GroupTopicResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class MaterialResponse {

    private String materialId;

    private String materialName;

    private String materialType;

    private String topicId;

    private String storageUrl;

    private boolean isShowAll;

    private String description;

    private List<GroupTopicResponse> groups;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime updatedDate;

    public MaterialResponse(Material material, List<GroupTopicResponse> groups){
        this.materialId = material.getMaterialId();
        this.materialName = material.getMaterialName();
        this.materialType = material.getMaterialType();
        this.topicId = material.getTopicId();
        this.storageUrl = material.getStorageUrl();
        this.isShowAll = material.isShowAll();
        this.description = material.getDescription();
        this.createdDate = material.getCreatedDate();
        this.updatedDate = material.getUpdatedDate();
        this.groups = groups;
    }
}
