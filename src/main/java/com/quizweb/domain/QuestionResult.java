package com.quizweb.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionResult {
    Question question;
    private int user_choice_index;
    private int correct_index;
    private boolean user_correct;

}
