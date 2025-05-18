package com.furkanozdemir.adapter.user.entity;

import lombok.*;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.time.OffsetDateTime;
import java.util.List;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {

    @Id
    private Long userId;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    private List<String> roles;

    private List<String> permissions;
}
