package com.diary.api.db.repository;

import com.diary.api.db.entity.Album;
import com.diary.api.db.entity.QAlbum;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlbumRepositorySupport {

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    QAlbum qAlbum = QAlbum.album;

    public Optional<List<Album>> getAlbumList(String userId) {
        List<Album> albums = jpaQueryFactory.select(qAlbum).from(qAlbum)
                .where(qAlbum.user.userId.eq(userId)).fetch();
        if(albums == null) return Optional.empty();
        return Optional.of(albums);
    }
}
