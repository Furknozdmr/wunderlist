package common.enums;

import com.furkanozdemir.common.enums.TaskStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskStatusTest {
    @Test
    void should_get_enums() {
        assertEquals(3, TaskStatus.values().length);
    }
}
