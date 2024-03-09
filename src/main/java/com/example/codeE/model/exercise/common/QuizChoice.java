package com.example.codeE.model.exercise.common;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "QuizChoice")
public class QuizChoice implements Comparable<QuizChoice> {
    @Id
    private String choiceId;

    @Field
    @NotNull(message = "Quiz choice content is required")
    private String content;

    public QuizChoice(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(QuizChoice o) {
        return this.getChoiceId().compareTo(o.getChoiceId());
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(!(o instanceof QuizChoice)){
            return false;
        }
        QuizChoice quizChoice = (QuizChoice) o;
        return quizChoice.getChoiceId().equals(this.getChoiceId()) && quizChoice.getContent().equals(this.getContent());
    }

    @Override
    public String toString() {
        return "QuizChoice{" +
                "choiceId='" + choiceId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
