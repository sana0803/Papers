package com.diary.api.db.repository;

import com.diary.api.db.entity.UserDiaryCover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDiaryCoverRepository extends JpaRepository<UserDiaryCover, Long> {
}
