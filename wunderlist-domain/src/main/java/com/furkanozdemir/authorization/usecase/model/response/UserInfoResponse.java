package com.furkanozdemir.authorization.usecase.model.response;

public record UserInfoResponse(String token, String refreshToken, String userId) {

}
