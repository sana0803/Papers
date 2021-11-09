package com.diary.api.db.repository;

import com.diary.api.db.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class NotificationRepositorySupport {
    @Autowired
    JPAQueryFactory jpaQueryFactory;

    QNotification qNotification = QNotification.notification;

    public Optional<List<Notification>> findAllByUserId(User user) {
        List<Notification> notifications = jpaQueryFactory.select(qNotification).from(qNotification)
                .where(qNotification.user.userId.eq(user.getUserId()))
                .orderBy(qNotification.id.desc()).fetch();

        if(notifications == null) return Optional.empty();
        return Optional.of(notifications);
    }

    @Transactional
    public boolean updateNotificationRead (User user, long notificationId) {
        Long row = jpaQueryFactory.update(qNotification)
                .set(qNotification.notificationRead, true)
                .where(qNotification.id.eq(notificationId))
                .execute();
        if (row > 0)
            return true;
        return false;
    }
}
