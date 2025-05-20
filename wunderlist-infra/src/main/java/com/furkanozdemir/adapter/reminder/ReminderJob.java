package com.furkanozdemir.adapter.reminder;

import com.furkanozdemir.adapter.todolist.entity.Task;
import com.furkanozdemir.adapter.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReminderJob {

    private final TaskRepository taskRepository;

    private final ReminderAdapter reminderAdapter;

    @Scheduled(fixedRate = 60000)
    public void checkReminders() {
        List<Task> tasks = taskRepository.findTasksByReminderDateBefore(LocalDate.now());
        tasks.forEach(task -> {
            reminderAdapter.triggerReminder(task);
            task.setReminderDate(null);
            taskRepository.save(task);
        });
    }

}
