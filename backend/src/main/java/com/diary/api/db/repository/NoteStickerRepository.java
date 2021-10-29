package com.diary.api.db.repository;

import com.diary.api.db.entity.NoteSticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteStickerRepository extends JpaRepository<NoteSticker, Long> {
}
