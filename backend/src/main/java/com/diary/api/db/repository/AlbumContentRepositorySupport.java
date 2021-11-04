package com.diary.api.db.repository;

import com.diary.api.db.entity.AlbumContent;
import com.diary.api.db.entity.QAlbumContent;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlbumContentRepositorySupport {

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    QAlbumContent qAlbumContent = QAlbumContent.albumContent;

    public Optional<List<AlbumContent>> getAlbumContentList(Long albumId) {
        List<AlbumContent> albumContents = jpaQueryFactory.select(qAlbumContent).from(qAlbumContent)
                .where(qAlbumContent.album.id.eq(albumId)).fetch();
        if(albumContents == null) return Optional.empty();
        return Optional.of(albumContents);
    }
}
