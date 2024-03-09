package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.QuizExercise;
import com.example.codeE.model.exercise.QuizSubmission;
import com.example.codeE.model.exercise.common.QuizAnswers;
import com.example.codeE.model.exercise.common.QuizChoice;
import com.example.codeE.repository.QuizAnswersRepository;
import com.example.codeE.repository.QuizQuestionRepository;
import com.example.codeE.repository.QuizSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class QuizSubmissionImpl implements QuizSubmissionService{
    @Autowired
    private QuizSubmissionRepository quizSubmissionRepository;

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    @Autowired
    private QuizAnswersRepository quizAnswersRepository;

    @Override
    public QuizSubmission createSubmission(QuizSubmission quizSubmission) {
        List<QuizAnswers> quizAnswersList = quizSubmission.getSubmission();
        for(int i=0; i<quizAnswersList.size(); i++){
            QuizAnswers savedQuizAns = this.quizAnswersRepository.save(quizAnswersList.get(i));
            quizAnswersList.get(i).setQuizAnswerId(savedQuizAns.getQuizAnswerId());
        }
        return this.quizSubmissionRepository.save(quizSubmission);
    }

    @Override
    public float gradeSubmission(QuizSubmission quizSubmission, QuizExercise quizExercise) {
        float correctAnsCnt = 0;
        for (QuizAnswers quizAnswers: quizSubmission.getSubmission()){
            List<QuizChoice> correctAnswers = this.quizQuestionRepository.findById(quizAnswers.getQuestionId()).get().getAnswers();
            List<QuizChoice> studentAnswers = quizAnswers.getAnswers();
            Collections.sort(correctAnswers);
            Collections.sort(studentAnswers);
            if(correctAnswers.equals(studentAnswers)){
                correctAnsCnt++;
            }
        }
        float questionQuantity = quizExercise.getQuestions().size();
        return 10 * (correctAnsCnt / questionQuantity);
    }
}
