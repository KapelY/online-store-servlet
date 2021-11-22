package com.study.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class User {
    Integer id;
    String email;
    String passwordHash;
    String salt;
}
