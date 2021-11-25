package com.diary.api.db.repository;

import com.diary.api.db.entity.NotificationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationInfoRepository extends JpaRepository<NotificationInfo, Long> {
    Optional<NotificationInfo> findById(Long id);
}