package common.model;

import com.furkanozdemir.common.CacheNames;
import com.furkanozdemir.common.CacheTTL;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CacheTTLTest {

    @Test
    void testJwtTokenTtlValues() {
        CacheTTL jwtTokenTtl = CacheTTL.JWT_TOKEN_TTL;
        assertEquals(CacheNames.JWT_TOKEN, jwtTokenTtl.getCacheName());
        assertEquals(1L, jwtTokenTtl.getDuration());
        assertEquals(TimeUnit.HOURS, jwtTokenTtl.getTimeType());
    }

    @Test
    void testJwtRefreshTokenTtlValues() {
        CacheTTL refreshTokenTtl = CacheTTL.JWT_REFRESH_TOKEN_TTL;
        assertEquals(CacheNames.JWT_REFRESH_TOKEN, refreshTokenTtl.getCacheName());
        assertEquals(1L, refreshTokenTtl.getDuration());
        assertEquals(TimeUnit.DAYS, refreshTokenTtl.getTimeType());
    }
}
