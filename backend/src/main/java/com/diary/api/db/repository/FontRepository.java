package com.diary.api.db.repository;

import com.diary.api.db.entity.Font;
import com.diary.api.db.entity.Sticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FontRepository extends JpaRepository<Font, Long> {
    @Query(value =
            "select f.id, f.font_name, f.font_price, f.font_url " +
            "from font f " +
            "join user_font uf " +
            "on f.id = uf.font_id " +
            "where uf.user_id = :userId", nativeQuery = true)
    List<Font> getFonts(@Param("userId") String userId);
}