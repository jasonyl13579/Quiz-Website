package com.quizweb.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Integer user_id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private boolean active;
    private boolean admin;

    private Integer open_quiz_id;
}
