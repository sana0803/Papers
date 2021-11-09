package com.diary.api.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
public class Notification extends BaseEntity {
    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "userId", nullable = false) // 알림을 받는 유저
    private User user;

    @ManyToOne
    @JoinColumn(name="notInfoId", referencedColumnName = "id", nullable = false)
    NotificationInfo notificationInfo;

    private LocalDate notificationDate;
    private LocalTime notificationTime;
    private String notificationContent;
    private boolean notificationRead;
    private String senderImageUrl;

    @PrePersist
    public void setUp() {
        this.notificationDate = LocalDate.now();
        this.notificationTime = LocalTime.now();
    }
}
