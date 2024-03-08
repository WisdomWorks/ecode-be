package com.example.codeE.service.material;

import com.example.codeE.model.group.Group;
import com.example.codeE.model.material.Material;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.request.material.CreateMaterialRequest;
import com.example.codeE.request.material.UpdateMaterialRequest;
import com.example.codeE.request.topic.UpdateTopicRequest;
import com.example.codeE.service.common.CommonService;

import java.util.List;

public interface MaterialService extends CommonService<Material, CreateMaterialRequest> {
    List<Material> getAllByTopicId(String topicId);
    Material updateById(UpdateMaterialRequest request);
    List<Group> getAllGroupsByMaterialId(String materialId);
    boolean removeViewPermission(String materialId, List<String> groupIds);
    boolean addViewPermission(String materialId, List<String> groupIds);
}
