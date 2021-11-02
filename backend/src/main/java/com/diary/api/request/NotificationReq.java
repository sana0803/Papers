package com.diary.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationReq {
    private String notificationContent;
    private int notificationInfoId;
}
