package com.diary.api.db.repository;

import com.diary.api.db.entity.AlbumContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumContentRepository extends JpaRepository<AlbumContent, Long> {
}
