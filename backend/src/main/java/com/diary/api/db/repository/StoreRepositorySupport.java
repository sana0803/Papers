package com.diary.api.db.repository;

import com.diary.api.db.entity.*;
import com.diary.api.response.FontRes;
import com.diary.api.response.StickerPackagesRes;
import com.diary.api.response.StickerRes;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository
public class StoreRepositorySupport {

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    QStickerPackage qStickerPackage = QStickerPackage.stickerPackage;
    QUserStickerPackage qUserStickerPackage = QUserStickerPackage.userStickerPackage;
    QFont qFont = QFont.font;
    QUserFont qUserFont = QUserFont.userFont;
    QDiaryCover qDiaryCover = QDiaryCover.diaryCover;
    QUserDiaryCover qUserDiaryCover = QUserDiaryCover.userDiaryCover;
    QSticker qSticker = QSticker.sticker;


    public Optional<List<StickerRes>> getStickerList(String userId, Long stickerPackageId){
        List<StickerRes> stickerResList = new ArrayList<>();
        List<Sticker> stickers = jpaQueryFactory.select(qSticker).from(qSticker)
                .where(qSticker.stickerPackage.id.eq(stickerPackageId)).fetch();
        if(stickers == null) return Optional.empty();

        for(Sticker sticker : stickers) {
            StickerRes stickerRes = new StickerRes(sticker.getId(), sticker.getStickerUrl());
            stickerResList.add(stickerRes);
        }
        return Optional.of(stickerResList);
    }
}
