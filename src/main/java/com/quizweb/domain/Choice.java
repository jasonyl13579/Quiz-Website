package com.quizweb.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Choice {
    private int choice_id;
    private int question_id;
    private String description;
    private boolean correct;


}