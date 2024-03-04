package com.example.codeE.service.material;

import com.example.codeE.model.material.Material;
import com.example.codeE.request.material.CreateMaterialRequest;
import com.example.codeE.request.material.UpdateMaterialRequest;
import com.example.codeE.service.common.CommonService;

public interface MaterialService extends CommonService<Material, CreateMaterialRequest> {
    Material updateById(UpdateMaterialRequest request);
}
