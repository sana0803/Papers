package com.diary.api.db.repository;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.diary.api.db.entity.*;
import com.diary.api.response.FontRes;
import com.diary.api.response.StickerPackagesRes;
import com.diary.api.response.StickerRes;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository
public class StoreRepositorySupport {

    private String S3_IMAGE_URL = "https://papers-bucket.s3.amazonaws.com/";

    @Value("${cloud.aws.credentials.access-key}")
    private String ACCESS_KEY;

    @Value("${cloud.aws.credentials.secret-key}")
    private String SECRET_KEY;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    QStickerPackage qStickerPackage = QStickerPackage.stickerPackage;
    QUserStickerPackage qUserStickerPackage = QUserStickerPackage.userStickerPackage;
    QFont qFont = QFont.font;
    QUserFont qUserFont = QUserFont.userFont;
    QDiaryCover qDiaryCover = QDiaryCover.diaryCover;
    QUserDiaryCover qUserDiaryCover = QUserDiaryCover.userDiaryCover;
    QSticker qSticker = QSticker.sticker;


    public Optional<List<String>> getStickerList(String userId, String stickerPackageName){
        AWSCredentials crd = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        AmazonS3 s3 = new AmazonS3Client(crd);
        ObjectListing objects = s3.listObjects("papers-bucket", "store/sticker/" + stickerPackageName);

        List<String> urls = new ArrayList<>();
        do {
            for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
                urls.add(S3_IMAGE_URL + objectSummary.getKey());
            }
        } while (objects.isTruncated());
        if(urls.size() > 0) urls.remove(0);
        return Optional.of(urls);
    }

    public Optional<List<String>> getDiaryCoverList(){
        AWSCredentials crd = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        AmazonS3 s3 = new AmazonS3Client(crd);
        ObjectListing objects = s3.listObjects("papers-bucket", "store/diary-cover");

        List<String> urls = new ArrayList<>();
        do {
            for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
                urls.add(S3_IMAGE_URL + objectSummary.getKey());
            }
        } while (objects.isTruncated());
        return Optional.of(urls);
    }

    public Optional<UserStickerPackage> getUserSticker(String userId, Long stickerPackageId) {
        UserStickerPackage userStickerPackage = jpaQueryFactory.select(qUserStickerPackage).from(qUserStickerPackage)
                .where(qUserStickerPackage.user.userId.eq(userId)
                .and(qUserStickerPackage.stickerPackage.id.eq(stickerPackageId))).fetchOne();
        if(userStickerPackage == null) return Optional.empty();
        return Optional.of(userStickerPackage);
    }

    public Optional<UserFont> getUserFont(String userId, Long fontId) {
        UserFont userFont = jpaQueryFactory.select(qUserFont).from(qUserFont)
                .where(qUserFont.user.userId.eq(userId)
                .and(qUserFont.font.id.eq(fontId))).fetchOne();
        if(userFont == null) return Optional.empty();
        return Optional.of(userFont);
    }

    public Optional<UserDiaryCover> getUserDiaryCover(String userId, Long diaryCoverId) {
        UserDiaryCover userDiaryCover = jpaQueryFactory.select(qUserDiaryCover).from(qUserDiaryCover)
                .where(qUserDiaryCover.user.userId.eq(userId)
                .and(qUserDiaryCover.diaryCover.id.eq(diaryCoverId))).fetchOne();
        if(userDiaryCover == null) return Optional.empty();
        return Optional.of(userDiaryCover);
    }
}
