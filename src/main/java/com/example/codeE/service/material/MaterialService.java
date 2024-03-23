package com.example.codeE.service.material;

import com.example.codeE.model.group.Group;
import com.example.codeE.model.material.Material;
import com.example.codeE.request.material.CreateMaterialRequest;
import com.example.codeE.request.material.MaterialResponse;
import com.example.codeE.request.material.UpdateMaterialRequest;
import com.example.codeE.service.common.CommonService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MaterialService extends CommonService<Material, CreateMaterialRequest> {
    Material CreateMaterial(CreateMaterialRequest createRequest, MultipartFile file);
    List<MaterialResponse> getAllByTopicId(String topicId);
    Material updateById(UpdateMaterialRequest request);
//    List<Group> getAllGroupsByMaterialId(String materialId);
    boolean removeViewPermission(String materialId, List<String> groupIds);
    boolean addViewPermission(String materialId, List<String> groupIds);
    List<MaterialResponse> getMaterialByUserId(String studentId, String materialId);
}
