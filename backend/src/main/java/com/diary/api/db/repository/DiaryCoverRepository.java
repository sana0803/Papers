package com.diary.api.db.repository;

import com.diary.api.db.entity.DiaryCover;
import com.diary.api.db.entity.UserDiaryCover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiaryCoverRepository extends JpaRepository<DiaryCover, Long> {
    Optional<DiaryCover> findById(Long id);
}
