package com.diary.api.service;


import com.diary.api.response.AlarmDataSet;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

public interface NotificationService {
    void addEmitter(String uuid, AlarmDataSet alarmDataSet);
    void removeEmitter(String uuid);
    void publish(String message);
    void publishToUsers(List<String> userIdList);
    void removeUser(String userId);
}
