package com.example.codeE.service.exercise.submission;

import com.example.codeE.helper.CloudStorageHelper;
import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.FileSubmission;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.repository.FileSubmissionRepository;
import com.example.codeE.repository.TopicRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.exercise.file.CreateFileSubmissionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.NoSuchElementException;

@Service
public class FileSubmissionImpl implements FileSubmissionService {
    @Autowired
    private FileSubmissionRepository fileSubmissionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private CloudStorageHelper cloudStorageHelper;
    @Override
    public FileSubmission createSubmission(CreateFileSubmissionRequest createRequest, MultipartFile file) {
        this.userRepository.findById(createRequest.getStudentId()).orElseThrow(() -> new NoSuchElementException("No student found by id: " + createRequest.getStudentId()));
        Exercise exercise = this.exerciseRepository.findById(createRequest.getExerciseId()).orElseThrow(() -> new NoSuchElementException("No exercise found by id: " + createRequest.getExerciseId()));

        try {
            String courseId = this.topicRepository.findById(exercise.getTopicId()).get().getCourseId();
            String store = "file-submissions/" + courseId + "/" + exercise.getTopicId() + "/";
            String url = cloudStorageHelper.uploadFile(file, true, store);
            createRequest.setUrl(url);
            var fileSubmission = new FileSubmission(createRequest, -1);
            return this.fileSubmissionRepository.save(fileSubmission);
        } catch (Exception e) {
            LoggerHelper.logError(e.getMessage());
            throw new IllegalArgumentException("Some thing wrong when create new material.");
        }
    }
}
