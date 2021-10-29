package com.diary.api.db.repository;

import com.diary.api.db.entity.NoteMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteMediaRepository extends JpaRepository<NoteMedia, Long> {
}
