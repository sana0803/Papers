package com.diary.api.db.repository;

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
