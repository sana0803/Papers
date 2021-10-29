package com.diary.api.db.repository;

import com.diary.api.db.entity.DiaryCover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryCoverRepository extends JpaRepository<DiaryCover, Long> {
}
