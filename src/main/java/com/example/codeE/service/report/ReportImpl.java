package com.example.codeE.service.report;

import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.repository.*;
import com.example.codeE.request.exercise.essay.EssaySubmissionsResponse;
import com.example.codeE.request.report.OverviewScoreReport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

public class ReportImpl implements ReportService{
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CourseStudentRepository courseStudentRepository;
    @Autowired
    private QuizSubmissionRepository quizSubmissionRepository;
    @Autowired
    private EssaySubmissionRepository essaySubmissionRepository;
    @Autowired
    private CodeSubmissionRepository codeSubmissionRepository;
    @Autowired


    @Override
    public OverviewScoreReport getOverviewScoreReportByExerciseId(String exerciseId) {
        OverviewScoreReport result = new OverviewScoreReport();
        var exercise = this.exerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise found by id: " +exerciseId));
        if(exercise.isShowAll()){
            String courseId = this.topicRepository.findById(exercise.getTopicId()).orElseThrow(() -> new NoSuchElementException("No topic found by id: " +exercise.getTopicId())).getCourseId();
            var courseStudents = this.courseStudentRepository.getAllStudentsInCourse(courseId);
            result.setNumberStudent(courseStudents.size());

        }else {

        }
        return result;
    }
    private OverviewScoreReport getAllSubmission(Exercise exercise){
        OverviewScoreReport result = new OverviewScoreReport();
        switch (exercise.getType()){
            case "code" ->{}
            case "file" ->{}
            case "quiz" ->{}
            case "essay" -> {
                var essays = this.essaySubmissionRepository.findAll();

            }
        }
        return result;
    }
}
