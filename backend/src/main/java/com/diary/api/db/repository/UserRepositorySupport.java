package com.diary.api.db.repository;

import com.diary.api.db.entity.QUser;
import com.diary.api.db.entity.User;
import com.diary.api.request.UserLoginReq;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public class UserRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QUser qUser = QUser.user;

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
}
