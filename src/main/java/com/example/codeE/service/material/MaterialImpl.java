package com.example.codeE.service.material;

import com.example.codeE.helper.CloudStorageHelper;
import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.model.material.Material;
import com.example.codeE.repository.GroupRepository;
import com.example.codeE.repository.MaterialRepository;
import com.example.codeE.repository.TopicRepository;
import com.example.codeE.repository.ViewPermissionMaterialRepository;
import com.example.codeE.request.group.GroupTopicResponse;
import com.example.codeE.request.material.CreateMaterialRequest;
import com.example.codeE.request.material.MaterialResponse;
import com.example.codeE.request.material.UpdateMaterialRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class MaterialImpl implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ViewPermissionMaterialRepository viewPermissionMaterialRepository;
    @Autowired
    private CloudStorageHelper cloudStorageHelper;

    @Override
    public Material createOne(CreateMaterialRequest request) {
        try {
            String materialId = UUID.randomUUID().toString();
            var material = new Material(materialId, request);
            return materialRepository.save(material);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            throw new IllegalArgumentException("Invalid request. Please check your request and try again. \n Error: " + e.getMessage());
        }
    }

    @Override
    public Material getById(String id) {
        return materialRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No material found with ID: " + id));
    }

    @Override
    public Material CreateMaterial(CreateMaterialRequest createRequest, MultipartFile file) {
        this.topicRepository.findById(createRequest.getTopicId())
                .orElseThrow(() -> new NoSuchElementException("No material found with ID: " + createRequest.getTopicId()));
        if (createRequest.getMaterialType().equals("file")) {
            try {
                String courseId = this.topicRepository.findById(createRequest.getTopicId()).get().getCourseId();
                String store = "materials/" + courseId + "/" + createRequest.getTopicId() + "/";
                String url = cloudStorageHelper.uploadFile(file, true, store);
                createRequest.setUrl(url);
                var material = new Material(UUID.randomUUID().toString(), createRequest);
                return this.materialRepository.save(material);
            } catch (Exception e) {
                LoggerHelper.logError(e.getMessage());
                throw new IllegalArgumentException("Some thing wrong when create new material.");
            }
        }
        if (createRequest.getMaterialType().equals("string")) {
            var material = new Material(UUID.randomUUID().toString(), createRequest);
            return this.materialRepository.save(material);
        }
        return null;
    }

    @Override
    public List<MaterialResponse> getAllByTopicId(String topicId) {
        List<Material> materials = materialRepository.findByTopicId(topicId);
        if (materials == null) {
            throw new NoSuchElementException("No material found with topicID: " + topicId);
        }
        List<MaterialResponse> materialResponses = new ArrayList<>();
        for (var material : materials) {
            var groups = this.viewPermissionMaterialRepository.getAllGroupsByMaterialId(material.getMaterialId());
            List<GroupTopicResponse> groupsResponse = new ArrayList<>();
            for (var g : groups) {
                var group = this.groupRepository.findById(g.getGroupId()).get();
                var groupItem = new GroupTopicResponse();
                groupItem.setGroupName(group.getGroupName());
                groupItem.setGroupId(group.getGroupId());
                groupsResponse.add(groupItem);
            }
            materialResponses.add(new MaterialResponse(material, groupsResponse));
        }
        return materialResponses;
    }

    @Override
    public List<Material> getAll() {
        return this.materialRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        var material = this.materialRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Material is not found"));
        try {
            if (material.getMaterialType().equals("file")) {
                if (cloudStorageHelper.deleteFile(material.getStorageUrl())) {
                    this.materialRepository.deleteById(id);
                } else
                    throw new IllegalArgumentException("Some thing wrong when delete material.");
            } else
                this.materialRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Some thing wrong when delete material.");
        }
    }

    @Override
    public Material updateById(UpdateMaterialRequest request) {
        var material = materialRepository.findById(request.getMaterialId()).orElseThrow(() -> new NoSuchElementException("No material found"));
        if (request.getDescription() != null) {
            material.setDescription(request.getDescription());
        }
        return materialRepository.save(material);
    }


    @Override
    public boolean removeViewPermission(String materialId, List<String> groupIds) {
        if (!this.materialRepository.existsById(materialId)) {
            throw new NoSuchElementException("No material found with ID: " + materialId);
        }
        for (String groupId : groupIds) {
            if (!this.groupRepository.existsById(groupId)) {
                throw new NoSuchElementException("No group found with ID: " + groupId);
            }
            this.viewPermissionMaterialRepository.removeViewPermission(materialId, groupId);
        }
        return true;
    }

    @Override
    public boolean addViewPermission(String materialId, List<String> groupIds, boolean isShowAll) {
        try {
            var material = this.materialRepository.findById(materialId).orElseThrow(() -> new NoSuchElementException("No material found with ID: " + materialId));
            for(var groupId :groupIds){
                this.groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("No group found with ID: " + groupId));
            }
            this.removeViewPermission(materialId, groupIds);
            for (String groupId : groupIds) {
                this.viewPermissionMaterialRepository.addViewPermission(materialId, groupId);
            }
            material.setShowAll(isShowAll);
            this.materialRepository.save(material);
        } catch (Exception e) {
            LoggerHelper.logError(e.getMessage());
            throw new RuntimeException("Can not grant permission for this materials");
        }
        return true;
    }

    @Override
    public List<MaterialResponse> getMaterialByUserId(String studentId, String materialId) {
        List<MaterialResponse> result = new ArrayList<>();
        var materials = this.materialRepository.getMaterialById(studentId, materialId);
        for (var material : materials) {
            var groups = this.viewPermissionMaterialRepository.getAllGroupsByMaterialId(material.getMaterialId());
            List<GroupTopicResponse> groupsResponse = new ArrayList<>();
            for (var g : groups) {
                var group = this.groupRepository.findById(g.getGroupId()).get();
                var groupItem = new GroupTopicResponse();
                groupItem.setGroupName(group.getGroupName());
                groupItem.setGroupId(group.getGroupId());
                groupsResponse.add(groupItem);
            }
            result.add(new MaterialResponse(material, groupsResponse));
        }
        return result;
    }
}
