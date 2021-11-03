package com.diary.api.db.repository;

import com.diary.api.db.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class DiaryCoverRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QUser qUser = QUser.user;
    QDiaryCover qDiaryCover = QDiaryCover.diaryCover;
    QUserDiaryCover qUserDiaryCover = QUserDiaryCover.userDiaryCover;


//    public boolean updateMileage (User user, int amount) {
//
////        Long row = jpaQueryFactory.select(qUser).from(qUser).join(qDiaryCover).on(qUser.userMileage).eq(qDiaryCover.id)
////                .set(qUser.userMileage, amount)
////                .where(qUser.userId.eq(user.getUserId()))
////                .execute();
//        if (row > 0)
//            return true;
//        return false;
//    }

    public List<DiaryCover> getDiaryCover(String userId) {
        List<DiaryCover> diaryCovers = jpaQueryFactory.select(qDiaryCover)
                .from(qDiaryCover)
                .join(qUserDiaryCover)
                .on(qDiaryCover.id.eq(qUserDiaryCover.diaryCover.id))
                .where(qUserDiaryCover.user.userId.eq(userId))
                .fetch();
        return diaryCovers;
    }
}
