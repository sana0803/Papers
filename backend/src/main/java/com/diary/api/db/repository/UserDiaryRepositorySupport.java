package com.diary.api.db.repository;

import com.diary.api.db.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDiaryRepositorySupport {
    @Autowired
    JPAQueryFactory jpaQueryFactory;

    QUserDiary qUserDiary = QUserDiary.userDiary;

    public boolean isOwner(long diaryId, String writerId) {
        List<UserDiary> userDiaryList = jpaQueryFactory.select(qUserDiary).from(qUserDiary)
                .where(qUserDiary.diary.id.eq(diaryId).and(qUserDiary.user.userId.eq(writerId))).fetch();

        if (userDiaryList.size() >= 1) return true;
        return false;
    }

    public UserDiary findByDiaryIdAndGuestId(Long diaryId, String guestId) {
        UserDiary userDiary = jpaQueryFactory.select(qUserDiary).from(qUserDiary)
                .where(qUserDiary.diary.id.eq(diaryId).and(qUserDiary.guestId.eq(guestId))).fetchOne();
        return userDiary;
    }
}
