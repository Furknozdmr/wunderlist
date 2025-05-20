package com.furkanozdemir.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ErrorResponseModel {

    private final String errorCode;

    private final String errorMessage;

    private final LocalDateTime occurredAt;

}
