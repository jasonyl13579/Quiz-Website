package com.quizweb.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizResult {
    private String time_taken;
    private String category_name;
    private String fullname;
    private int count;
    private float score;
    private int quiz_id;

}
