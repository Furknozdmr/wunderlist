package com.furkanozdemir.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

@Getter
@RequiredArgsConstructor
public enum CacheTTL {

    JWT_TOKEN_TTL(CacheNames.JWT_TOKEN, 1L, TimeUnit.HOURS),

    JWT_REFRESH_TOKEN_TTL(CacheNames.JWT_REFRESH_TOKEN, 1L, TimeUnit.DAYS);

    private final String cacheName;

    private final Long duration;

    private final TimeUnit timeType;

}
