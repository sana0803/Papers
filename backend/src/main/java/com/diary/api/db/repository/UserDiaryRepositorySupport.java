package com.diary.api.db.repository;

import com.diary.api.db.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDiaryRepositorySupport {
    @Autowired
    JPAQueryFactory jpaQueryFactory;

    QUserDiary qUserDiary = QUserDiary.userDiary;

    public boolean isOwner(long diaryId, String writerId) {
        UserDiary userDiary = jpaQueryFactory.select(qUserDiary).from(qUserDiary)
                .where(qUserDiary.diary.id.eq(diaryId).and(qUserDiary.user.userId.eq(writerId))).fetchOne();

        if (userDiary != null) return true;
        return false;
    }
}
