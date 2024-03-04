package com.example.codeE.service.material;

import com.example.codeE.model.course.Course;
import com.example.codeE.model.material.Material;
import com.example.codeE.repository.MaterialRepository;
import com.example.codeE.request.material.CreateMaterialRequest;
import com.example.codeE.request.material.UpdateMaterialRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MaterialImpl implements MaterialService{

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public Material createOne(CreateMaterialRequest request) {
        try {
            String materialId = UUID.randomUUID().toString();
            var material = new Material(materialId, request);
            return materialRepository.save(material);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            return null;
        }
    }

    @Override
    public Material getById(String id) {
        Optional<Material> material = materialRepository.findById(id);
        return material.orElse(null);
    }

    @Override
    public List<Material> getAll() {
        return this.materialRepository.findAll();
    }

    @Override
    public boolean deleteById(String id) {
        if(materialRepository.existsById(id)){
            this.materialRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Material updateById(UpdateMaterialRequest request) {
        Material material = materialRepository.findById(request.getMaterialId()).get();

        if(request.getMaterialType() != null){
            material.setMaterialType(request.getMaterialType());
        } else if (request.getTopicId() != null) {
            material.setTopicId(request.getTopicId());
        } else if (request.getStorageUrl() != null) {
            material.setStorageUrl(request.getStorageUrl());
        } else if (request.getDescription() != null) {
            material.setDescription(request.getDescription());
        }
        return materialRepository.save(material);
    }
}
