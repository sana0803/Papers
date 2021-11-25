package com.diary.api.db.repository;

import com.diary.api.db.entity.NoteHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteHashtagRepository extends JpaRepository<NoteHashtag, Long> {
}
