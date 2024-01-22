package com.example.codeE.model.material;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Material {

    @NonNull
    private String materialId;
    @NonNull
    private String topicId;
    @NonNull
    private String materialName;
    private String description;
    @NonNull
    private String url;
    @NonNull
    private Date createDate;
    @NonNull
    private Boolean isPublic;
}