package com.diary.api.db.repository;

import com.diary.api.db.entity.Diary;
import com.diary.api.db.entity.User;
import com.diary.api.db.entity.UserDiary;
import com.diary.api.response.UserSearchRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 유저 일기장정보 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */

@Repository
public interface UserDiaryRepository extends JpaRepository<UserDiary, Long> {
    List<UserDiary> findByGuestId(String guestId);
    List<UserDiary> findAllByDiaryId(Long diaryId);
}
