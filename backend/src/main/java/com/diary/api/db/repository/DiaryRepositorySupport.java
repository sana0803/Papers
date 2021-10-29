package com.diary.api.db.repository;


import com.diary.api.db.entity.Diary;
import com.diary.api.db.entity.QDiary;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;


/**
 * 일기장 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class DiaryRepositorySupport {

//    @Autowired
//    private JPAQueryFactory jpaQueryFactory;
//
//    QDiary qDiary = QDiary.diary;
//
//    // 유저아이디로 다이어리 조회
//    public Optional<List<Diary>> getDiaryListByUserId(String userId) {
//        List<Diary> list = jpaQueryFactory.select(qDiary).from(qDiary)
//                .where(qDiary.user.userId.eq(userId)).fetch();
//
//        if (list == null) return Optional.empty();
//
//        return Optional.of(list);
//    }

}
