package com.quizweb.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quiz {
    private int quiz_id;
    private int user_id;
    private int category_id;
    private String name;
    private String time_start;
    private String time_end;
}
