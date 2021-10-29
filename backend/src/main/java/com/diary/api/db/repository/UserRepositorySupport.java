package com.diary.api.db.repository;

import com.diary.api.db.entity.QUser;
import com.diary.api.db.entity.User;
import com.diary.api.request.UserLoginReq;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QUser qUser = QUser.user;

}
