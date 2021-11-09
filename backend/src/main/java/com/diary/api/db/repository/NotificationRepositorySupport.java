package com.diary.api.db.repository;

import com.diary.api.db.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NotificationRepositorySupport {
    @Autowired
    JPAQueryFactory jpaQueryFactory;

    QNotification qNotification = QNotification.notification;

    public Optional<List<Notification>> findAllByUserId(User user) {
        List<Notification> notifications = jpaQueryFactory.select(qNotification).from(qNotification)
                .where(qNotification.user.userId.eq(user.getUserId())).fetch();

        if(notifications == null) return Optional.empty();
        return Optional.of(notifications);
    }
}
