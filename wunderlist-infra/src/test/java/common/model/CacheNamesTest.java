package common.model;

import com.furkanozdemir.common.CacheNames;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CacheNamesTest {

    @Test
    void testCacheNamesConstants() {
        assertEquals("wunderlist::jwt-token", CacheNames.JWT_TOKEN);
        assertEquals("wunderlist::jwt-refresh-token", CacheNames.JWT_REFRESH_TOKEN);
    }
}

