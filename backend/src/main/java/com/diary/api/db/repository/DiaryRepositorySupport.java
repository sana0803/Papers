package com.diary.api.db.repository;

/**
 * 일기장 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
import com.diary.api.db.entity.Diary;
import com.diary.api.db.entity.QDiary;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DiaryRepositorySupport {

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    QDiary qDiary = QDiary.diary;

    public Optional<Diary> getDiary(Long diaryId){
        Diary diary = jpaQueryFactory.select(qDiary).from(qDiary)
                .where(qDiary.id.eq(diaryId)).fetchOne();
        if(diary == null) return Optional.empty();
        return Optional.ofNullable(diary);
    }

}
