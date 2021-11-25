package com.diary.api.db.repository;

import com.diary.api.db.entity.EmotionLog;
import com.diary.api.db.entity.QEmotionLog;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmotionLogRepositorySupport {

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    QEmotionLog qEmotionLog = QEmotionLog.emotionLog;

    public Optional<List<EmotionLog>> getEmotionLog(Long noteId, String userId) {
        List<EmotionLog> emotionLogs = jpaQueryFactory.select(qEmotionLog).from(qEmotionLog)
                .where(qEmotionLog.note.id.eq(noteId).and(qEmotionLog.user.userId.eq(userId))).fetch();
        if(emotionLogs == null) return Optional.empty();
        return Optional.of(emotionLogs);
    }
}
