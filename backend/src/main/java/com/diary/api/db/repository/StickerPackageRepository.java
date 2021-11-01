package com.diary.api.db.repository;

import com.diary.api.db.entity.StickerPackage;
import com.diary.api.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StickerPackageRepository extends JpaRepository<StickerPackage, Long> {
//    List<StickerPackage> findAllByUserId(String userId);
}