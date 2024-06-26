package com.example.codeE.service.exercise;

import com.example.codeE.constant.Constant;
import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.model.exercise.*;
import com.example.codeE.model.exercise.common.SessionExercise;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.*;
import com.example.codeE.request.exercise.*;
import com.example.codeE.request.exercise.file.response.FilePreviewResponse;
import com.example.codeE.request.group.GroupTopicResponse;
import com.example.codeE.request.user.StudentSubmissionInformation;
import com.example.codeE.service.exercise.common.SessionExerciseService;
import com.example.codeE.service.exercise.submission.CodeSubmissionService;
import com.example.codeE.service.exercise.submission.EssaySubmissionService;
import com.example.codeE.service.exercise.submission.FileSubmissionService;
import com.example.codeE.service.exercise.submission.QuizSubmissionService;
import com.example.codeE.util.DateTimeUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ExerciseImpl implements ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private EssayExerciseRepository essayExerciseRepository;
    @Autowired
    private QuizExerciseRepository quizExerciseRepository;
    @Autowired
    private CodeExerciseRepository codeExerciseRepository;
    @Autowired
    private FileExerciseRepository fileExerciseRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupStudentRepository groupStudentRepository;
    @Autowired
    private CodeSubmissionService codeSubmissionService;
    @Autowired
    private EssaySubmissionService essaySubmissionService;
    @Autowired
    private QuizSubmissionService quizSubmissionService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private FileSubmissionService fileSubmissionService;
    @Autowired
    private SessionExerciseRepository sessionExerciseRepository;
    @Autowired
    private SessionExerciseService sessionExerciseService;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Exercise saveQuizExercise(QuizExercise exercise) {
        return this.exerciseRepository.save(exercise);
    }

    @Override
    public Exercise saveEsayExercise(EssayExercise exercise) {
        return this.exerciseRepository.save(exercise);
    }

    @Override
    public Exercise saveCodeExercise(CodeExercise exercise) {
        return this.exerciseRepository.save(exercise);
    }

    @Override
    public Exercise saveFileExercise(FileExercise exercise) {
        return this.exerciseRepository.save(exercise);
    }

    @Override
    public ExerciseStudentResponse getPreviewExercise(String exerciseId, String studentId) {
        var exercise = this.exerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise found with ID: " + exerciseId));
        var available = this.isReTemp(exercise.getExerciseId(), studentId, exercise.getType(), exercise.getReAttempt());
        return new ExerciseStudentResponse(exercise, available);
    }

    @Override
    public FilePreviewResponse getFilePreviewExercise(String exerciseId, String studentId) {
        var exercise = this.exerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise found with ID: " + exerciseId));
        var available = this.isReTemp(exercise.getExerciseId(), studentId, exercise.getType(), exercise.getReAttempt());
        var fileExercise = this.fileExerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No file exercise found with ID: " + exerciseId));
        return new FilePreviewResponse(fileExercise, fileExercise.getQuestion(), available);
    }

    @Override
    public List<ExerciseResponse> getExercisesByCourseId(String courseId) {
        List<String> topicIdList = new ArrayList<>();
        for (Topic topic : this.topicRepository.findAll()) {
            System.out.println(topic.getTopicId());
            if (topic.getCourseId().equals(courseId)) topicIdList.add(topic.getTopicId());
        }
        List<ExerciseResponse> exercises = new ArrayList<>();
        for (String id : topicIdList) {
            exercises.addAll(this.getExercisesByTopicId(id));
        }
        for (int i = 0; i < exercises.size(); i++) {
            switch (exercises.get(i).getType()) {
                case "essay" -> {
                    var submission = this.essaySubmissionService.getEssaySubmissionByExerciseId(exercises.get(i).getExerciseId());
                    List<StudentSubmissionInformation> submissionResponse = new ArrayList<>();
                    for (var sub : submission) {
                        var userInfor = this.userRepository.findById(sub.getStudentId()).get();
                        submissionResponse.add(new StudentSubmissionInformation(userInfor.getUserId(), userInfor.getName(), sub.getDateSubmit(), sub.getScore(), sub.isReviewable()));
                    }
                    exercises.get(i).setStudents(submissionResponse);
                }
                case "quiz" -> {
                    var submission = this.quizSubmissionService.getQuizSubmissionByExerciseId(exercises.get(i).getExerciseId());
                    List<StudentSubmissionInformation> submissionResponse = new ArrayList<>();
                    for (var sub : submission) {
                        var userInfor = this.userRepository.findById(sub.getStudentId()).get();
                        submissionResponse.add(new StudentSubmissionInformation(userInfor.getUserId(), userInfor.getName(), sub.getDateSubmit(), sub.getScore(), sub.isReviewable()));
                    }
                    exercises.get(i).setStudents(submissionResponse);
                }
                //code exercise here
            }
        }
        return exercises;
    }

    @Override
    public Exercise getExerciseById(String exerciseId) {
        return this.exerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise found with ID: " + exerciseId));
    }

    @Override
    public Exercise getDetailExercise(String exerciseId, String key, String studentId, String userUrgent, HttpServletRequest request, HttpServletResponse response) {
        var exercise = this.exerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise found with ID: " + exerciseId));
        if (!exercise.getKey().equals(key))
            throw new IllegalArgumentException("Invalid enrollment key. Please double-check and try again.");
        else if (!isReTemp(exercise.getExerciseId(), studentId, exercise.getType(), exercise.getReAttempt()))
            throw new DataIntegrityViolationException("You have already submitted the exercise the maximum number of times allowed.");
        else if (exercise.getKey().equals(key) && isReTemp(exercise.getExerciseId(), studentId, exercise.getType(), exercise.getReAttempt())) {
            // login id from cookie
            String loginId = "";
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("LoginSessionId".equals(cookie.getName())) {
                        loginId = cookie.getValue();
                    }
                }
            }
            //check session exercise
            var sessionExercises = this.sessionExerciseRepository.findByStudentId(studentId);
            if (sessionExercises.isEmpty()) {
                // if it does not have any session has been saved create new one.
                // get time now
                LocalDateTime dateNow = LocalDateTime.now();
                //user Urgent ?
                var session = new SessionExercise(loginId, studentId, exerciseId, DateTimeUtil.formatToIso(dateNow), "");
                sessionExerciseRepository.save(session);
                return exercise;
            } else {
                var session = sessionExercises.get(0);
                Date timeStart = new Date();
                try {
                    var timeString = session.getTimeStart();
                    SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_TIME_ISO_FORMAT);
                    timeStart = sdf.parse(timeString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date now = new Date();
                if ((long) exercise.getDurationTime() * 1000 * 60 + timeStart.getTime() < now.getTime()) {
                    this.sessionExerciseRepository.deleteById(session.getSessionId());
                    LocalDateTime dateNow = LocalDateTime.now();
                    //user Urgent ?
                    session = new SessionExercise(loginId, studentId, exerciseId, DateTimeUtil.formatToIso(dateNow), "");
                    sessionExerciseRepository.save(session);
                }
                if (session.getLoginId().equals(loginId)) {
                    if (session.getExerciseId().equals(exerciseId)) {
                        return exercise;
                    } else {
                        throw new IllegalArgumentException("Student need to complete another current exercise before participating in a new one.");
                    }
                } else {
                    throw new IllegalArgumentException("Student is using another browser to take an exercise.");
                }
                }

        } else
            throw new IllegalArgumentException("Failed to retrieve exercise information.");
    }


    private boolean isReTemp(String exerciseId, String userId, String type, int reTemp) {
        switch (type) {
            case "quiz" -> {
                var quizSubmission = this.quizSubmissionService.getQuizSubmissionByUserId(exerciseId, userId);
                if (quizSubmission.isEmpty() || quizSubmission.size() < reTemp)
                    return true;
            }
            case "essay" -> {
                var quizSubmission = this.essaySubmissionService.getEssaySubmissionByUserId(exerciseId, userId);
                if (quizSubmission.isEmpty() || quizSubmission.size() < reTemp)
                    return true;
            }
            case "code" -> {
                var codeSubmission = this.codeSubmissionService.getCodeSubmissionByUserId(exerciseId, userId);
                if (codeSubmission.isEmpty() || codeSubmission.size() < reTemp)
                    return true;
            }
            case "file" -> {
                var fileSubmission = this.fileSubmissionService.getFileSubmissionByUserId(exerciseId, userId);
                if (fileSubmission.isEmpty() || fileSubmission.size() < reTemp)
                    return true;
            }
        }
        return false;
    }

    @Override
    public void deleteExerciseById(String exerciseId) {
        this.exerciseRepository.deleteById(exerciseId);
    }

    @Override
    public ExerciseResponse modifiedPermission(CreatePermissionExerciseRequest request) {
        try {
            var exercise = this.exerciseRepository.findById(request.getExerciseId()).orElseThrow(() -> new NoSuchElementException("No exercise found with ID: " + request.getExerciseId()));
            exercise.setPublicGroupIds(request.getGroupIds());
            exercise.setShowAll(request.isShowAll());
            this.exerciseRepository.save(exercise);
            switch (exercise.getType()) {
                case "code" -> {
                    var code = this.codeExerciseRepository.findById(exercise.getExerciseId()).orElseThrow(() -> new NoSuchElementException("No code exercise found with ID: " + request.getExerciseId()));
                    code.setPublicGroupIds(request.getGroupIds());
                    code.setShowAll(request.isShowAll());
                    codeExerciseRepository.save(code);
                }
                case "quiz" -> {
                    var quiz = this.quizExerciseRepository.findById(exercise.getExerciseId()).orElseThrow(() -> new NoSuchElementException("No quiz exercise found with ID: " + request.getExerciseId()));
                    quiz.setPublicGroupIds(request.getGroupIds());
                    quiz.setShowAll(request.isShowAll());
                    quizExerciseRepository.save(quiz);
                }
                case "essay" -> {
                    var essay = this.essayExerciseRepository.findById(exercise.getExerciseId()).orElseThrow(() -> new NoSuchElementException("No essay exercise found with ID: " + request.getExerciseId()));
                    essay.setPublicGroupIds(request.getGroupIds());
                    essay.setShowAll(request.isShowAll());
                    essayExerciseRepository.save(essay);
                }
                case "file" -> {
                    var file = this.fileExerciseRepository.findById(exercise.getExerciseId()).orElseThrow(() -> new NoSuchElementException("No file exercise found with ID: " + request.getExerciseId()));
                    file.setPublicGroupIds(request.getGroupIds());
                    file.setShowAll(request.isShowAll());
                    fileExerciseRepository.save(file);
                }
            }
            var groupResponse = new ArrayList<GroupTopicResponse>();
            for (var item : exercise.getPublicGroupIds()) {
                var group = this.groupRepository.findById(item).get();
                groupResponse.add(new GroupTopicResponse(group.getGroupName(), group.getGroupId()));
            }
            return new ExerciseResponse(exercise, groupResponse);
        } catch (Exception e) {
            throw new RuntimeException("Something wrong when change permission.");
        }
    }

    @Override
    public List<AllStudentSubmissionResponse> getAllStudentSubmission(String courseId, String userId) {
        this.userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("No user found"));
        List<AllStudentSubmissionResponse> responses = new ArrayList<>();
        try {
            List<String> topicIdList = new ArrayList<>();
            for (Topic topic : this.topicRepository.findAll()) {
                if (topic.getCourseId().equals(courseId)) topicIdList.add(topic.getTopicId());
            }
            List<ExerciseResponse> exercises = new ArrayList<>();
            for (String id : topicIdList) {
                exercises.addAll(this.getExercisesByTopicId(id));
            }
            for (var item : exercises) {
                switch (item.getType()) {
                    case "essay" -> {
                        var essay = this.essaySubmissionService.getLastEssaySubmissionByUserId(item.getExerciseId(), userId);
                        if (essay != null)
                            responses.add(new AllStudentSubmissionResponse(essay, item.getExerciseName()));
                    }
                    case "code" -> {
                        var code = this.codeSubmissionService.getLastCodeSubmissionByUserId(item.getExerciseId(), userId);
                        if (code != null)
                            responses.add(new AllStudentSubmissionResponse(code, item.getExerciseName()));
                    }
                    case "quiz" -> {
                        var quiz = this.quizSubmissionService.getLastQuizSubmissionByUserId(item.getExerciseId(), userId);
                        if (quiz != null)
                            responses.add(new AllStudentSubmissionResponse(quiz, item.getExerciseName()));
                    }
                    case "file" -> {
                        var file = this.fileSubmissionService.getLastFileSubmissionByUserId(item.getExerciseId(), userId);
                        if (file != null)
                            responses.add(new AllStudentSubmissionResponse(file, item.getExerciseName()));
                    }
                }
            }
        } catch (Exception e) {
            LoggerHelper.logError(e.getMessage());
            throw new RuntimeException("Something wrong when get exercise in this topic");
        }
        return responses;
    }

    @Override
    public String exportResultExercise(String exerciseId, String type) {
        List<ExportResultExcelModel> data = new ArrayList<>();
        switch (type) {
            case "file" -> {
                var submissions = this.fileSubmissionService.getFileSubmissionByExerciseId(exerciseId);
                for (var item : submissions) {
                    var student = this.userRepository.findById(item.getStudentId()).get();
                    data.add(new ExportResultExcelModel(student.getName(), student.getUsername(), item.getDateSubmit(), item.getDateGrade(), item.getScore().toString()));
                }
            }
            case "code" -> {
                var submissions = this.codeSubmissionService.getAllSubmissionByExerciseId(exerciseId);
                for (var item : submissions) {
                    var student = this.userRepository.findById(item.getStudentId()).get();
                    data.add(new ExportResultExcelModel(student.getName(), student.getUsername(), item.getDateSubmit(), item.getDateGrade(), item.getScore().toString()));
                }
            }
            case "quiz" -> {
                var submissions = this.quizSubmissionService.getQuizSubmissionByExerciseId(exerciseId);
                for (var item : submissions) {
                    var student = this.userRepository.findById(item.getStudentId()).get();
                    data.add(new ExportResultExcelModel(student.getName(), student.getUsername(), item.getDateSubmit(), item.getDateGrade(), item.getScore().toString()));
                }
            }
            case "essay" -> {
                var submissions = this.essaySubmissionService.getEssaySubmissionByExerciseId(exerciseId);
                for (var item : submissions) {
                    var student = this.userRepository.findById(item.getStudentId()).get();
                    data.add(new ExportResultExcelModel(student.getName(), student.getUsername(), item.getDateSubmit(), item.getDateGrade(), item.getScore().toString()));
                }
            }
        }

        return "";
    }

    @Override
    public List<ExerciseResponse> getExercisesByTopicId(String topicId) {
        try {
            List<Exercise> exercises = this.exerciseRepository.findAll();
            List<ExerciseResponse> result = new ArrayList<>();
            for (var item : exercises) {
                if (item.getTopicId().equals(topicId)) {
                    result.add(new ExerciseResponse(item, getGroupResponse(item.getPublicGroupIds())));
                }
            }
            return result;
        } catch (Exception e) {
            LoggerHelper.logError(e.getMessage());
            throw new RuntimeException("Something wrong when get exercise in this topic");
        }
    }

    @Override
    public List<ExerciseResponse> getExercisesByUserId(String topicId, String userId) {
        try {
            List<Exercise> exercises = this.exerciseRepository.findAll();
            List<ExerciseResponse> result = new ArrayList<>();
            for (var item : exercises) {
                if (item.isShowAll()) {
                    if (item.getTopicId().equals(topicId)) {
                        result.add(new ExerciseResponse(item, getGroupResponse(item.getPublicGroupIds())));
                    }
                } else {
                    if (item.getTopicId().equals(topicId)) {
                        for (String g : item.getPublicGroupIds()) {
                            var check = this.groupStudentRepository.isStudentInGroup(userId, g);
                            if (check != null) {
                                result.add(new ExerciseResponse(item, getGroupResponse(item.getPublicGroupIds())));
                                break;
                            }
                        }
                    }
                }
            }
            return result;
        } catch (Exception e) {
            LoggerHelper.logError(e.getMessage());
            throw new RuntimeException("Something wrong when get exercise in this topic");
        }
    }

    private List<GroupTopicResponse> getGroupResponse(List<String> groupIds) {
        List<GroupTopicResponse> groupTopicResponses = new ArrayList<>();
        for (String g : groupIds) {
            var group = this.groupRepository.findById(g).get();
            groupTopicResponses.add(new GroupTopicResponse(group.getGroupName(), group.getGroupId()));
        }
        return groupTopicResponses;
    }

    @Override
    public List<Exercise> getAllExerciseInCourse(String courseId) {
        this.courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("No course found with ID: " + courseId));
        List<Topic> topics = topicRepository.findByCourseId(courseId);
        List<Exercise> exercises = new ArrayList<>();

        for (Topic topic : topics) {
            List<Exercise> topicExercises = exerciseRepository.getAllExercisesByTopicId(topic.getTopicId());
            exercises.addAll(topicExercises);
        }

        return exercises;
    }

    @Override
    public void exportStudentScores(List<Exercise> exercises, List<User> students, HttpServletResponse response) throws IOException {
        // Create a new workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Student Scores");

        // Create the header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Username");
        for (int i = 0; i < exercises.size(); i++) {
            headerRow.createCell(i + 1).setCellValue(exercises.get(i).getExerciseName());
        }

        // Populate the student scores
        int rowNum = 1;
        for (User student : students) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(student.getUsername());

            // Get the student's latest submission score for each exercise
            for (int i = 0; i < exercises.size(); i++) {
                Exercise exercise = exercises.get(i);
                Float score = getLatestScoreByStudentAndExercise(student.getUserId(), exercise.getExerciseId(), exercise.getType());
                System.out.println(score);
                if (score != null) {
                    row.createCell(i + 1).setCellValue(score);
                } else {
                    row.createCell(i + 1).setCellValue("");
                }
            }
        }

        // Set the response headers for Excel file download
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=student_scores.xlsx");

        // Write the workbook to the response output stream
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    @Override
    public Float getLatestScoreByStudentAndExercise(String studentId, String exerciseId, String exerciseType) {
        if (studentId == null || exerciseId == null || exerciseType == null) {
            return null;
        }
        switch (exerciseType) {
            case "code":
                CodeSubmission codeSubmission = codeSubmissionService.getLastCodeSubmissionByUserId(exerciseId, studentId);
                return codeSubmission != null && !codeSubmission.getScore().equals(-1f) ? roundScore(codeSubmission.getScore()) : null;
            case "essay":
                EssaySubmission essaySubmission = essaySubmissionService.getLastEssaySubmissionByUserId(exerciseId, studentId);
                return essaySubmission != null && !essaySubmission.getScore().equals(-1f) ? roundScore(essaySubmission.getScore()) : null;
            case "quiz":
                QuizSubmission quizSubmission = quizSubmissionService.getLastQuizSubmissionByUserId(exerciseId, studentId);
                return quizSubmission != null && !quizSubmission.getScore().equals(-1f) ? roundScore(quizSubmission.getScore()) : null;
            case "file":
                FileSubmission fileSubmission = fileSubmissionService.getLastFileSubmissionByUserId(exerciseId, studentId);
                return fileSubmission != null && !fileSubmission.getScore().equals(-1f) ? roundScore(fileSubmission.getScore()) : null;
            default:
                return null;
        }
    }

    private Float roundScore(Float score) {
        if (score == null) {
            return null;
        }
        return Math.round(score * 100.0f) / 100.0f;
    }
}
