package com.diary.api.db.repository;

/**
 * 일기장 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
import com.diary.api.db.entity.Diary;
import com.diary.api.db.entity.DiaryCover;
import com.diary.api.db.entity.QDiary;
import com.diary.api.db.entity.QDiaryCover;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.checkerframework.checker.index.qual.PolyUpperBound;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DiaryRepositorySupport {

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    QDiary qDiary = QDiary.diary;
    QDiaryCover qDiaryCover = QDiaryCover.diaryCover;


    public Optional<Diary> getDiary(Long diaryId){
        Diary diary = jpaQueryFactory.select(qDiary).from(qDiary)
                .where(qDiary.id.eq(diaryId)).fetchOne();
        if(diary == null) return Optional.empty();
        return Optional.ofNullable(diary);
    }

    public Optional<DiaryCover> getDiaryCover(Long coverId) {
        DiaryCover diaryCover = jpaQueryFactory.select(qDiaryCover).from(qDiaryCover)
                .where(qDiaryCover.id.eq(coverId)).fetchOne();
        if(diaryCover == null) return Optional.empty();
        return Optional.ofNullable(diaryCover);
    }
}
