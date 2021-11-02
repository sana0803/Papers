package com.diary.api.response;

import com.diary.api.db.entity.Notification;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class NotificationRes {
    private Long id;
    private String notificationContent;
    private LocalDate notificationDate;
    private LocalTime notificationTime;
    private boolean notificationRead;
    private String notificationType;
    private Long notInfoId;

    public static NotificationRes of(Notification notification) {
        NotificationRes res = new NotificationRes();
        res.id = notification.getId();
        res.notificationContent = notification.getNotificationContent();
        res.notificationDate = notification.getNotificationDate();
        res.notificationTime = notification.getNotificationTime();
        res.notificationRead = notification.isNotificationRead();
        res.notificationType = notification.getNotificationType();
        res.notInfoId = notification.getNotificationInfo().getId();
        return res;
    }
}
