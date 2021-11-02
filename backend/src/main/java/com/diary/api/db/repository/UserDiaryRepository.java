package com.diary.api.db.repository;

import com.diary.api.db.entity.UserDiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDiaryRepository extends JpaRepository<UserDiary, Long> {
}
