package com.example.codeE.request.topic;

import com.example.codeE.model.material.Material;
import com.example.codeE.model.topic.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicByUserResponse {
    private Topic topic;
    private List<Material> materials;
}
