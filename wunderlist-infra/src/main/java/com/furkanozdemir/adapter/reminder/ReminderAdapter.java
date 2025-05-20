package com.furkanozdemir.adapter.reminder;

import com.furkanozdemir.adapter.todolist.entity.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReminderAdapter {


    public void triggerReminder(Task task) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(task.getAssignUser().getEmail());
        message.setSubject("Görev Hatırlatması: " + task.getTitle());
        message.setText(task.getDescription() + "\n\nSon Tarih: " + task.getReminderDate());

        log.info("Reminder mail is send");
    }


}
