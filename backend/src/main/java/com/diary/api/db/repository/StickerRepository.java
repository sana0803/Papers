package com.diary.api.db.repository;

import com.diary.api.db.entity.Sticker;
import com.diary.api.db.entity.StickerPackage;
import com.diary.api.db.entity.UserStickerPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StickerRepository extends JpaRepository<Sticker, Long> {
    @Query(value = "select * from sticker where sticker_package_id = :stickerPackageId", nativeQuery = true)
    List<Sticker> getStickerList(@Param("stickerPackageId") Long stickerPackageId);
}