package com.diary.api.db.repository;

import com.diary.api.db.entity.*;
import com.diary.api.request.UserLoginReq;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QUser qUser = QUser.user;
    QUserDiaryCover qUserDiaryCover = QUserDiaryCover.userDiaryCover;
    QNotification qNotification = QNotification.notification;


    @Transactional
    public boolean readNotification (String userId, Long id) {
        Long row = jpaQueryFactory.update(qNotification)
                .set(qNotification.notificationRead, true)
                .where(qNotification.user.userId.eq(userId).and(qNotification.id.eq(id)))
                .execute();
        if (row > 0)
            return true;
        return false;
    }

    @Transactional
    public boolean updateMileage (User user, int amount) {
        Long row = jpaQueryFactory.update(qUser)
                .set(qUser.userMileage, amount)
                .where(qUser.userId.eq(user.getUserId()))
                .execute();
        if (row > 0)
            return true;
        return false;
    }

    @Transactional
    public boolean updateUser (User user) {
        Long row = jpaQueryFactory.update(qUser)
                .set(qUser.userName, user.getUserName())
                .set(qUser.userNickname, user.getUserNickname())
                .set(qUser.userProfile, user.getUserProfile())
                .where(qUser.userId.eq(user.getUserId()))
                .execute();
        if (row > 0)
            return true;
        return false;
    }

    public List<Notification> getNotifications(String userId) {
        List<Notification> notifications = jpaQueryFactory.select(qNotification)
                .from(qNotification)
                .where(qNotification.user.userId.eq(userId))
                .fetch();
        return notifications;
    }

    public List<User> getUsersLikeUserId(String userIdSubString) {
        List<User> users = jpaQueryFactory.select(qUser)
                .from(qUser)
                .where(qUser.userId.like("%" + userIdSubString + "%"))
                .fetch();
        return users;

    }
}
