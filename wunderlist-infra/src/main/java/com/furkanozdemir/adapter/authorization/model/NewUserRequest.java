package com.furkanozdemir.adapter.authorization.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewUserRequest {

    private String name;

    private String surname;

    private String email;

    private String password;

}
