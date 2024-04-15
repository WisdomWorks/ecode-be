package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.QuizExercise;
import com.example.codeE.model.exercise.common.QuizChoice;
import com.example.codeE.model.exercise.common.QuizQuestion;
import com.example.codeE.repository.*;
import com.example.codeE.request.exercise.quiz.QuizDetailResponse;
import com.example.codeE.request.exercise.quiz.UpdateQuizExerciseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class QuizExerciseImpl implements QuizExerciseService{
    @Autowired
    private QuizExerciseRepository quizExerciseRepository;
    @Autowired
    private QuizChoiceRepository quizChoiceRepository;
    @Autowired
    private QuizQuestionRepository quizQuestionRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Override
    public QuizExercise createQuizExercise(QuizExercise quizExercise) {
        this.topicRepository.findById(quizExercise.getTopicId()).orElseThrow(() -> new NoSuchElementException("No topic found by Id: "+ quizExercise.getTopicId()));
        if (quizExercise.getReAttempt() <= 0) {
            quizExercise.setReAttempt(1);
        }
        List<QuizQuestion> questions = quizExercise.getQuestions();
        for(int i=0; i<questions.size(); i++){
            QuizQuestion quizQuestion = quizExercise.getQuestions().get(i);
            List<QuizChoice> choices = questions.get(i).getChoices();
            for(int j=0; j<choices.size(); j++){
                QuizChoice savedChoice = quizChoiceRepository.save(choices.get(j));
                quizQuestion.getChoices().get(j).setChoiceId(savedChoice.getChoiceId());
                quizExercise.getQuestions().get(i).getChoices().get(j).setChoiceId(savedChoice.getChoiceId());
                List<QuizChoice> answers = questions.get(i).getAnswers();
                for(int k=0; k<answers.size(); k++){
                    if(answers.get(k).getContent().equals(choices.get(j).getContent())){
                        quizQuestion.getAnswers().get(k).setChoiceId(savedChoice.getChoiceId());
                        quizExercise.getQuestions().get(i).getAnswers().get(k).setChoiceId(savedChoice.getChoiceId());
                    }
                }
            }
            quizExercise.getQuestions().get(i).setQuestionId(quizQuestionRepository.save(quizQuestion).getQuestionId());
        }
        return quizExerciseRepository.save(quizExercise);
    }

    @Override
    public QuizExercise getQuizExerciseById(String exerciseId) {
        return quizExerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise quiz found by Id: "+ exerciseId));
    }
    @Override
    public QuizDetailResponse getQuizExerciseDetail(String exerciseId){
       var quiz = quizExerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise essay found by Id: "+ exerciseId));
       return new QuizDetailResponse(quiz);
    }
    @Override
    public QuizQuestion getQuizQuestionByQuestionId(String questionId) {
        return this.quizQuestionRepository.findById(questionId).get();
    }

    @Override
    public QuizExercise updateQuizExercise(String exerciseId, UpdateQuizExerciseRequest updateExercise) {
        try {
            QuizExercise quizExercise = this.quizExerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise found by Id: " + exerciseId));
            for(var deleteQuestion : quizExercise.getQuestions()){
                this.quizQuestionRepository.deleteById(deleteQuestion.getQuestionId());
            }
            List<QuizQuestion> questions = updateExercise.getQuestions();
            for(int i=0; i<questions.size(); i++){
                QuizQuestion quizQuestion = updateExercise.getQuestions().get(i);
                List<QuizChoice> choices = questions.get(i).getChoices();
                for(int j=0; j<choices.size(); j++){
                    QuizChoice savedChoice = quizChoiceRepository.save(choices.get(j));
                    quizQuestion.getChoices().get(j).setChoiceId(savedChoice.getChoiceId());
                    updateExercise.getQuestions().get(i).getChoices().get(j).setChoiceId(savedChoice.getChoiceId());
                    List<QuizChoice> answers = questions.get(i).getAnswers();
                    for(int k=0; k<answers.size(); k++){
                        if(answers.get(k).getContent().equals(choices.get(j).getContent())){
                            quizQuestion.getAnswers().get(k).setChoiceId(savedChoice.getChoiceId());
                            updateExercise.getQuestions().get(i).getAnswers().get(k).setChoiceId(savedChoice.getChoiceId());
                        }
                    }
                }
                updateExercise.getQuestions().get(i).setQuestionId(quizQuestionRepository.save(quizQuestion).getQuestionId());
            }
            QuizExercise updateQuiz = new QuizExercise(exerciseId ,updateExercise, quizExercise.isShowAll(), quizExercise.getPublicGroupIds());
            this.exerciseRepository.save(updateQuiz);
        return this.quizExerciseRepository.save(updateQuiz);
        } catch (RuntimeException e) {
            throw new RuntimeException("Something wrong when update quiz exercise");
        }
    }

    @Override
    public void deleteQuizExerciseById(String exerciseId) {
        this.quizExerciseRepository.deleteById(exerciseId);
    }
}
