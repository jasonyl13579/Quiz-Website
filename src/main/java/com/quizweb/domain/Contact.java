package com.quizweb.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {
    private int contact_id;
    private String subject;
    private String message;
    private String email;
    private String timestamp;
}