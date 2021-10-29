package com.diary.api.db.repository;


import com.diary.api.db.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 일기장 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

}
