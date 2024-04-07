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
import com.example.codeE.request.exercise.file.response.FileSubmissionsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<FileSubmissionsResponse> getFileSubmissionsByExerciseId(String exerciseId) {
        List<FileSubmission> submissions = this.fileSubmissionRepository.findAll();
        var result = new ArrayList<FileSubmissionsResponse>();
        for (var item : submissions) {
            if (item.getExerciseId().equals(exerciseId)) {
                var student = this.userRepository.findById(item.getStudentId()).orElseThrow(() -> new NoSuchElementException("No student found by id: " + item.getStudentId()));
                result.add(new FileSubmissionsResponse(item, student, new Exercise()));
            }
        }
        return result;
    }

    @Override
    public FileSubmissionsResponse getFileSubmissionById(String submissionId) {
        var fileSubmission = this.fileSubmissionRepository.findById(submissionId).orElseThrow(() -> new NoSuchElementException("No Submission found by id: " + submissionId));
        var student = this.userRepository.findById(fileSubmission.getStudentId()).orElseThrow(() -> new NoSuchElementException("No student found by id: " + fileSubmission.getStudentId()));
        var exercise = this.exerciseRepository.findById(fileSubmission.getExerciseId()).orElseThrow(() -> new NoSuchElementException("No exercise found by id: " + fileSubmission.getExerciseId()));
        return new FileSubmissionsResponse(fileSubmission, student, exercise);
    }

    @Override
    public List<FileSubmission> getFileSubmissionByUserId(String exerciseId, String userId) {
        List<FileSubmission> submissions = this.fileSubmissionRepository.findAll();
        var result = new ArrayList<FileSubmission>();
        for (var item : submissions) {
            if (item.getExerciseId().equals(exerciseId) && item.getStudentId().equals(userId)) {
                result.add(item);
            }
        }
        return result;
    }
}
