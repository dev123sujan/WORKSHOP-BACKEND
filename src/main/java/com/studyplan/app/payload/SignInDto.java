package com.studyplan.app.payload;

import lombok.Data;

@Data
public class SignInDto {
    private String email;
    private String password;
}
