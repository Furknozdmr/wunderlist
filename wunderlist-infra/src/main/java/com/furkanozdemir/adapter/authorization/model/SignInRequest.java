package com.furkanozdemir.adapter.authorization.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {

    private String email;

    private String password;

}
