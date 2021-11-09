package com.diary.api.db.repository;

import com.diary.api.db.entity.UserFont;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserFontRepository extends JpaRepository<UserFont, Long> {
    Optional<UserFont> findByFontId(Long fontId);
}
