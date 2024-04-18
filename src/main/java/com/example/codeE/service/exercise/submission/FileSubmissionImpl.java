package com.example.codeE.service.exercise.submission;

import com.example.codeE.helper.CloudStorageHelper;
import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.FileSubmission;
import com.example.codeE.model.group.Group;
import com.example.codeE.repository.*;
import com.example.codeE.request.exercise.AllSubmissionResponse;
import com.example.codeE.request.exercise.SubmissionDetail;
import com.example.codeE.request.exercise.file.CreateFileSubmissionRequest;
import com.example.codeE.request.exercise.file.response.FileSubmissionsResponse;
import com.example.codeE.request.group.GroupTopicResponse;
import com.example.codeE.request.report.OverviewScoreReport;
import com.example.codeE.service.group.GroupService;
import com.example.codeE.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FileSubmissionImpl implements FileSubmissionService {
    @Autowired
    private FileSubmissionRepository fileSubmissionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private CloudStorageHelper cloudStorageHelper;
    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupStudentRepository groupStudentRepository;
    @Autowired
    private CourseStudentRepository courseStudentRepository;

    @Override
    public FileSubmission createSubmission(CreateFileSubmissionRequest createRequest, MultipartFile file) {
        this.userRepository.findById(createRequest.getStudentId()).orElseThrow(() -> new NoSuchElementException("No student found by id: " + createRequest.getStudentId()));
        Exercise exercise = this.exerciseRepository.findById(createRequest.getExerciseId()).orElseThrow(() -> new NoSuchElementException("No exercise found by id: " + createRequest.getExerciseId()));

        try {
            String courseId = this.topicRepository.findById(exercise.getTopicId()).get().getCourseId();
            String store = "file-submissions/" + courseId + "/" + exercise.getTopicId() + "/";
            String url = cloudStorageHelper.uploadFile(file, true, store);
            var fileSubmission = new FileSubmission(createRequest, url, -1);
            return this.fileSubmissionRepository.save(fileSubmission);
        } catch (Exception e) {
            LoggerHelper.logError(e.getMessage());
            throw new IllegalArgumentException("Some thing wrong when create new material.");
        }
    }

    @Override
    public AllSubmissionResponse<SubmissionDetail> getFileSubmissionsByExerciseId(String exerciseId) {
        var exercise = this.exerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise found"));
        List<FileSubmission> submissions = this.fileSubmissionRepository.findAll();
        var listSubmissions = new ArrayList<SubmissionDetail>();
        for (var item : submissions) {
            if (item.getExerciseId().equals(exerciseId)) {
                var student = this.userRepository.findById(item.getStudentId()).orElseThrow(() -> new NoSuchElementException("No student found by id: " + item.getStudentId()));
                List<GroupTopicResponse> returnedGroups = this.userService.getAllGroupsByUserId(student.getUserId());
                listSubmissions.add(new SubmissionDetail(student, item, returnedGroups));
            }
        }
        List<Group> groups = new ArrayList<>();
        for (var item : exercise.getPublicGroupIds()) {
            groups.add(this.groupService.getById(item));
        }
        List<String> groupIds = new ArrayList<>();
        if (!exercise.isShowAll()) {
            for (var group : groups) {
                groupIds.add(group.getGroupId());
            }
        }
        var report = this.getOverviewScoreReportByExerciseId(exerciseId, groupIds);
        return new AllSubmissionResponse<SubmissionDetail>(exercise, listSubmissions, report, exercise.isShowAll() ? null : groups);
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

    @Override
    public FileSubmission getLastFileSubmissionByUserId(String exerciseId, String userId) {
        List<FileSubmission> submissions = this.fileSubmissionRepository.findAll();
        var result = new ArrayList<FileSubmission>();
        for (var item : submissions) {
            if (item.getExerciseId().equals(exerciseId) && item.getStudentId().equals(userId)){
                result.add(item);
            }
        }
        result.sort(new Comparator<FileSubmission>() {
            @Override
            public int compare(FileSubmission o1, FileSubmission o2) {
                return o1.getDateSubmit().compareTo(o2.getDateSubmit());
            }
        });
        if (!result.isEmpty())
            return result.get(result.size() - 1);
        else return null;
    }

    @Override
    public FileSubmission gradeSubmission(String submissionId, float score, String comment) {
        if (score < 0 || score > 10) {
            throw new IllegalArgumentException("Score must be between 0 and 10");
        }
        FileSubmission fileSubmission = this.fileSubmissionRepository.findById(submissionId).orElseThrow(() -> new NoSuchElementException("No submission found by Id: " + submissionId));
        fileSubmission.setScore(score);
        fileSubmission.setTeacherComment(comment);
        this.fileSubmissionRepository.save(fileSubmission);
        return fileSubmission;
    }

    @Override
    public List<FileSubmission> getFileSubmissionByExerciseId(String exerciseId) {
        List<FileSubmission> submissions = this.fileSubmissionRepository.findAll();
        var result = new ArrayList<FileSubmission>();
        for (var item : submissions) {
            if (item.getExerciseId().equals(exerciseId)) {
                result.add(item);
            }
        }
        return result;
    }

    public OverviewScoreReport getOverviewScoreReportByExerciseId(String exerciseId, List<String> groupId) {
        OverviewScoreReport result = new OverviewScoreReport();
        var exercise = exerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise found"));
        ;
        if (exercise.isShowAll()) {
            String courseId = topicRepository.findById(exercise.getTopicId()).orElseThrow(() -> new NoSuchElementException("No topic found")).getCourseId();
            var courseStudents = courseStudentRepository.getAllStudentsInCourse(courseId);
            int AScoreCount = 0, BScoreCount = 0, CScoreCount = 0, NumberSubmission = 0;
            result.setNumberStudent(courseStudents.size());
            for (var item : courseStudents) {
                float score = getScoreStudent(item.getStudentId(), exercise);
                if (score != -1) {
                    NumberSubmission++;
                    if (score < 5)
                        CScoreCount++;
                    else if (score < 8)
                        BScoreCount++;
                    else
                        AScoreCount++;
                }
            }
            result.setAScore(AScoreCount);
            result.setBScore(BScoreCount);
            result.setCScore(CScoreCount);
            result.setNumberSubmission(NumberSubmission);
        } else {
            List<String> hasGetSubmission = new ArrayList<>();
            int AScoreCount = 0, BScoreCount = 0, CScoreCount = 0, NumberSubmission = 0;
            for (String gId : exercise.getPublicGroupIds()) {
                if (groupId.contains(gId)) {
                    var groupStudents = groupStudentRepository.getStudentInGroup(gId);
                    for (var item : groupStudents) {
                        if (!hasGetSubmission.contains(item.getUserId())) {
                            hasGetSubmission.add(item.getUserId());
                            float score = getScoreStudent(item.getUserId(), exercise);
                            if (score != -1) {
                                NumberSubmission++;
                                if (score < 5)
                                    CScoreCount++;
                                else if (score < 8)
                                    BScoreCount++;
                                else
                                    AScoreCount++;
                            }
                        }
                    }
                    result.setNumberStudent(result.getNumberStudent() + groupStudents.size());
                }
            }
            result.setAScore(AScoreCount);
            result.setBScore(BScoreCount);
            result.setCScore(CScoreCount);
            result.setNumberSubmission(NumberSubmission);
        }
        return result;
    }

    private float getScoreStudent(String studentId, Exercise exercise) {
        var quiz = this.getLastFileSubmissionByUserId(exercise.getExerciseId(), studentId);
        if (quiz != null)
            return quiz.getScore();
        else return -1;

    }
}
