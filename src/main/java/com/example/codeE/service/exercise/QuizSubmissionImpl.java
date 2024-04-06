package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.QuizSubmission;
import com.example.codeE.model.exercise.common.QuizAnswers;
import com.example.codeE.model.exercise.common.QuizChoice;
import com.example.codeE.model.exercise.common.QuizQuestion;
import com.example.codeE.repository.*;
import com.example.codeE.request.exercise.quiz.QuizSubmissionsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizSubmissionImpl implements QuizSubmissionService{
    @Autowired
    private QuizSubmissionRepository quizSubmissionRepository;
    @Autowired
    private QuizQuestionRepository quizQuestionRepository;
    @Autowired
    private QuizAnswersRepository quizAnswersRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Override
    public QuizSubmission createSubmission(QuizSubmission quizSubmission) {
        this.userRepository.findById(quizSubmission.getStudentId()).orElseThrow(() -> new NoSuchElementException("No student found by id: " + quizSubmission.getStudentId()));
        this.exerciseRepository.findById(quizSubmission.getExerciseId()).orElseThrow(() -> new NoSuchElementException("No exercise found by id: " + quizSubmission.getExerciseId()));
        List<QuizAnswers> quizAnswersList = quizSubmission.getSubmission();
        for(int i=0; i<quizAnswersList.size(); i++){
            QuizAnswers savedQuizAns = this.quizAnswersRepository.save(quizAnswersList.get(i));
            quizAnswersList.get(i).setQuizAnswerId(savedQuizAns.getQuizAnswerId());
        }
        return this.quizSubmissionRepository.save(quizSubmission);
    }

    @Override
    public List<QuizSubmission> getQuizSubmissionByExerciseId(String exerciseId) {
        List<QuizSubmission> submissions = this.quizSubmissionRepository.findAll();
        var result = new ArrayList<QuizSubmission>();
        for (var item : submissions) {
            if (item.getExerciseId().equals(exerciseId)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public List<QuizSubmissionsResponse> getQuizSubmissionsByExerciseId(String exerciseId) {
        List<QuizSubmission> submissions = this.quizSubmissionRepository.findAll();
        var result = new ArrayList<QuizSubmissionsResponse>();
        for (var item : submissions) {
            if (item.getExerciseId().equals(exerciseId)) {
                var student = this.userRepository.findById(item.getStudentId()).orElseThrow(() -> new NoSuchElementException("No student found by id: " + item.getStudentId()));
                result.add(new QuizSubmissionsResponse(item, student, new Exercise()));
            }
        }
        return result;
    }

    @Override
    public QuizSubmissionsResponse getStudentQuizSubmission(String submissionId) {
        var quiz = this.quizSubmissionRepository.findById(submissionId).orElseThrow(() -> new NoSuchElementException("No Submission found"));
        var user = this.userRepository.findUserByUserId(quiz.getStudentId());
        var exercise = this.exerciseRepository.findById(quiz.getExerciseId()).orElseThrow(() -> new NoSuchElementException("No Exercise found"));
        return new QuizSubmissionsResponse(quiz, user, exercise);
    }

    @Override
    public List<QuizSubmission> getQuizSubmissionByUserId(String exerciseId, String userId) {
        List<QuizSubmission> submissions = this.quizSubmissionRepository.findAll();
        var result = new ArrayList<QuizSubmission>();
        for (var item : submissions) {
            if (item.getExerciseId().equals(exerciseId) && item.getStudentId().equals(userId)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public float gradeSubmission(List<QuizAnswers> quizSubmission,List<QuizQuestion> quizExercise) {
        float correctAnsCnt = 0;
        for (QuizAnswers quizAnswers: quizSubmission){
            List<QuizChoice> correctAnswers = this.quizQuestionRepository.findById(quizAnswers.getQuestionId()).get().getAnswers();
            List<QuizChoice> studentAnswers = quizAnswers.getAnswers();
            Collections.sort(correctAnswers);
            Collections.sort(studentAnswers);
            if(correctAnswers.equals(studentAnswers)){
                correctAnsCnt++;
            }
        }
        float questionQuantity = quizExercise.size();
        return 10 * (correctAnsCnt / questionQuantity);
    }

    @Override
    public QuizSubmission getLastQuizSubmissionByUserId(String exerciseId, String userId) {
        List<QuizSubmission> submissions = this.quizSubmissionRepository.findAll();
        var result = new ArrayList<QuizSubmission>();
        for (var item : submissions) {
            if (item.getExerciseId().equals(exerciseId) && item.getStudentId().equals(userId)) {
                result.add(item);
            }
        }
        result.sort(new Comparator<QuizSubmission>() {
            @Override
            public int compare(QuizSubmission o1, QuizSubmission o2) {
                return o1.getDateSubmit().compareTo(o2.getDateSubmit());
            }
        });
        if (!result.isEmpty())
        return result.get(result.size() - 1);
        else return null;
    }
}
