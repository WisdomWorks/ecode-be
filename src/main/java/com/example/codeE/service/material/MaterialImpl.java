package com.example.codeE.service.material;

import com.example.codeE.model.material.Material;
import com.example.codeE.repository.MaterialRepository;
import com.example.codeE.request.material.CreateMaterialRequest;
import com.example.codeE.request.material.UpdateMaterialRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
        return materialRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No material found with ID:" + id));
    }

    @Override
    public List<Material> getAll() {
        return this.materialRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        if(materialRepository.existsById(id)){
            this.materialRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Material not found with id " + id);
        }
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
