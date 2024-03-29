package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.QuizSubmission;
import com.example.codeE.model.exercise.common.QuizAnswers;
import com.example.codeE.model.exercise.common.QuizChoice;
import com.example.codeE.model.exercise.common.QuizQuestion;
import com.example.codeE.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

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
    public QuizSubmission getStudentQuizSubmission(String submissionId) {
        return this.quizSubmissionRepository.findById(submissionId).orElseThrow(() -> new NoSuchElementException("No Submission found"));
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
}
