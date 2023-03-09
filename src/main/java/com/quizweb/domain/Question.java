package com.quizweb.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {
    private int question_id;
    private int category_id;
    private String description;
    private boolean active;
    private List<Choice> choices;
    private int user_choice_id;

    public int getCorrectChoiceIndex() {
        for (int i=0;i<choices.size();i++) {
            if (choices.get(i).isCorrect()) {
                return i;
            }
        }
        return -1;
    }

    public int getUserChoiceIndex() {
        for (int i=0;i<choices.size();i++) {
            if (choices.get(i).getChoice_id() == user_choice_id) {
                return i;
            }
        }
        return -1;
    }

    public boolean isUserCorrect() {
        return getCorrectChoiceIndex() == getUserChoiceIndex();
    }
}


