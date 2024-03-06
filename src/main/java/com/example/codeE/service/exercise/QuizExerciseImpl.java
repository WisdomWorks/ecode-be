package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.QuizExercise;
import com.example.codeE.model.exercise.common.QuizChoice;
import com.example.codeE.model.exercise.common.QuizQuestion;
import com.example.codeE.repository.QuizChoiceRepository;
import com.example.codeE.repository.QuizExerciseRepository;
import com.example.codeE.repository.QuizQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizExerciseImpl implements QuizExerciseService{
    @Autowired
    private QuizExerciseRepository quizExerciseRepository;

    @Autowired
    private QuizChoiceRepository quizChoiceRepository;

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    @Override
    public QuizExercise createQuizExercise(QuizExercise quizExercise) {
        List<QuizQuestion> questions = quizExercise.getQuestions();
        for(int i=0; i<questions.size(); i++){
            QuizQuestion savedQuizQuestion = quizQuestionRepository.save(questions.get(i));
            quizExercise.getQuestions().get(i).setQuestionId(savedQuizQuestion.getQuestionId());

            List<QuizChoice> choices = questions.get(i).getChoices();
            for(int j=0; j<choices.size(); j++){
                QuizChoice savedChoice = quizChoiceRepository.save(choices.get(j));
                quizExercise.getQuestions().get(i).getChoices().get(j).setChoiceId(savedChoice.getChoiceId());

                List<QuizChoice> answers = questions.get(i).getAnswers();
                for(int k=0; k<answers.size(); k++){
                    if(answers.get(k).getContent().equals(choices.get(j).getContent())){
                        quizExercise.getQuestions().get(i).getAnswers().get(k).setChoiceId(savedChoice.getChoiceId());
                    }
                }
            }
        }
        return quizExerciseRepository.save(quizExercise);
    }
}
