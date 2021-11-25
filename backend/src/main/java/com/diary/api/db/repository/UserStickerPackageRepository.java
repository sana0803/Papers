package com.diary.api.db.repository;

import com.diary.api.db.entity.StickerPackage;
import com.diary.api.db.entity.UserStickerPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserStickerPackageRepository extends JpaRepository<UserStickerPackage, Long> {
    @Query(value = "select * from user_sticker_package where user_id = :userId", nativeQuery = true)
    List<UserStickerPackage> getStickerPackageList(@Param("userId") String userId);
    Optional<UserStickerPackage> findByStickerPackageId(Long stickerPackageId);
}