package com.diary.api.request;

import com.diary.api.db.entity.NotificationInfo;
import com.diary.api.db.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NotificationReq {
    private String notificationContent;
    private NotificationInfo notificationInfo;
    private String senderImageUrl;
    private User user;
    private Long diaryId;
    private Long noteId;
}
