package com.diary.api.db.repository;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.diary.api.db.entity.*;
import com.diary.api.request.NoteEmotionReq;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class NoteRepositorySupport {

    private String S3_IMAGE_URL = "https://papers-bucket.s3.amazonaws.com/";

    @Value("${cloud.aws.credentials.access-key}")
    private String ACCESS_KEY;

    @Value("${cloud.aws.credentials.secret-key}")
    private String SECRET_KEY;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QNote qNote = QNote.note;
    QSticker qSticker = QSticker.sticker;
    QEmotion qEmotion = QEmotion.emotion;
    QNoteHashtag qNoteHashtag = QNoteHashtag.noteHashtag;
    QNoteMedia qNoteMedia = QNoteMedia.noteMedia;
    QFont qFont = QFont.font;
    QNoteDesign qNoteDesign = QNoteDesign.noteDesign;
    QNoteLayout qNoteLayout = QNoteLayout.noteLayout;
    QNoteSticker qNoteSticker = QNoteSticker.noteSticker;
    QEmotionInfo qEmotionInfo = QEmotionInfo.emotionInfo;
    QUserDiary qUserDiary = QUserDiary.userDiary;

    public Optional<List<Note>> getNoteList(String userId) {
        List<Note> notes = jpaQueryFactory.select(qNote).from(qNote)
                .where(qNote.user.userId.eq(userId)).fetch();
        if(notes == null) return Optional.empty();
        return Optional.of(notes);
    }
    public Optional<List<Note>> getMonthNote(int month, String userId) {
        List<Note> notes = jpaQueryFactory.select(qNote).from(qNote)
                .where(qNote.noteCreateDate.month().eq(month)
                .and(qNote.diary.id.in(
                        jpaQueryFactory.select(qUserDiary.diary.id).from(qUserDiary)
                        .where(qUserDiary.user.userId.eq(userId)
                        .or(qUserDiary.guestId.eq(userId)))
                ))).fetch();
        if(notes == null) return Optional.empty();
        return Optional.ofNullable(notes);
    }

    public Optional<List<NoteSticker>> getNoteStickers(Long noteId){
        List<NoteSticker> stickers = jpaQueryFactory.select(qNoteSticker).from(qNoteSticker)
                .where(qNoteSticker.note.id.eq(noteId)).fetch();
        for(NoteSticker noteSticker : stickers) noteSticker.setNote(null);
        if(stickers == null) return Optional.empty();
        return Optional.ofNullable(stickers);
    }

    public Optional<List<Emotion>> getNoteEmotions(Long noteId){
        List<Emotion> emotions = jpaQueryFactory.select(qEmotion).from(qEmotion)
                .where(qEmotion.note.id.eq(noteId)).fetch();
        for(Emotion emotion : emotions) emotion.setNote(null);
        if(emotions == null) return Optional.empty();
        return Optional.ofNullable(emotions);
    }

    public Optional<List<String>> getNoteHashtags(Long noteId){
        List<String> noteHashtags = jpaQueryFactory.select(qNoteHashtag.tagValue).from(qNoteHashtag)
                .where(qNoteHashtag.note.id.eq(noteId)).fetch();
        if(noteHashtags == null) return Optional.empty();
        return Optional.ofNullable(noteHashtags);
    }

    public Optional<List<String>> getNoteMedias(Long noteId){
        List<String> noteMedias = jpaQueryFactory.select(qNoteMedia.mediaUrl).from(qNoteMedia)
                .where(qNoteMedia.note.id.eq(noteId)).fetch();
        if(noteMedias == null) return Optional.empty();
        return Optional.ofNullable(noteMedias);
    }

    public Optional<Note> getNote(Long noteId) {
        Note note = jpaQueryFactory.select(qNote).from(qNote)
                .where(qNote.id.eq(noteId)).fetchOne();
        if(note == null) return Optional.empty();
        return Optional.ofNullable(note);
    }

    public Optional<Sticker> getSticker(Long stickerId){
        Sticker sticker = jpaQueryFactory.select(qSticker).from(qSticker)
                .where(qSticker.id.eq(stickerId)).fetchOne();
        if(sticker == null) return Optional.empty();
        return Optional.ofNullable(sticker);
    }

    public Optional<Font> getFont(Long fontId){
        Font font = jpaQueryFactory.select(qFont).from(qFont)
                .where(qFont.id.eq(fontId)).fetchOne();
        if(font == null) return Optional.empty();
        return Optional.ofNullable(font);
    }

    public Optional<Emotion> getEmotion(Long emotionId){
        Emotion emotion = jpaQueryFactory.select(qEmotion).from(qEmotion)
                .where(qEmotion.id.eq(emotionId)).fetchOne();
        if(emotion == null) return Optional.empty();
        return Optional.ofNullable(emotion);
    }

    public Optional<EmotionInfo> getEmotionInfo(Long emotionInfoId) {
        EmotionInfo emotionInfo = jpaQueryFactory.select(qEmotionInfo).from(qEmotionInfo)
                .where(qEmotionInfo.id.eq(emotionInfoId)).fetchOne();
        if(emotionInfo == null) return Optional.empty();
        return Optional.ofNullable(emotionInfo);
    }

    public Optional<NoteDesign> getNoteDesign(Long noteDesignId){
        NoteDesign noteDesign = jpaQueryFactory.select(qNoteDesign).from(qNoteDesign)
                .where(qNoteDesign.id.eq(noteDesignId)).fetchOne();
        if(noteDesign == null) return Optional.empty();
        return Optional.ofNullable(noteDesign);
    }

    public Optional<NoteLayout> getNoteLayout(Long noteLayoutId){
        NoteLayout noteLayout = jpaQueryFactory.select(qNoteLayout).from(qNoteLayout)
                .where(qNoteLayout.id.eq(noteLayoutId)).fetchOne();
        if(noteLayout == null) return Optional.empty();
        return Optional.ofNullable(noteLayout);
    }

    @Transactional
    public void deleteNoteMedia(Long noteId) {
        jpaQueryFactory.delete(qNoteMedia).where(qNoteMedia.note.id.eq(noteId)).execute();
    }

    @Transactional
    public void deleteNoteHashtag(Long noteId) {
        jpaQueryFactory.delete(qNoteHashtag).where(qNoteHashtag.note.id.eq(noteId)).execute();
    }

    @Transactional
    public void deleteNoteEmotion(Long noteId) {
        jpaQueryFactory.delete(qEmotion).where(qEmotion.note.id.eq(noteId)).execute();
    }

    @Transactional
    public void deleteNoteEmotionByUser(NoteEmotionReq noteEmotionReq){
        jpaQueryFactory.delete(qEmotion).where(qEmotion.note.id.eq(noteEmotionReq.getNoteId())
                .and(qEmotion.user.userId.eq(noteEmotionReq.getWriterId()))
                .and(qEmotion.emotionInfo.id.eq(noteEmotionReq.getEmotionInfoId()))).execute();
    }

    @Transactional
    public void deleteNoteSticker(Long noteId) {
        jpaQueryFactory.delete(qNoteSticker).where(qNoteSticker.note.id.eq(noteId)).execute();
    }
    public List<String> getImageFiles(String userId, Long diaryId){
        AWSCredentials crd = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        AmazonS3 s3 = new AmazonS3Client(crd);
        ObjectListing objects = s3.listObjects("papers-bucket", "diary-file/" + userId + "/" + diaryId);

        List<String> urls = new ArrayList<>();
        do {
            for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
                urls.add(S3_IMAGE_URL + objectSummary.getKey());
            }
//            objects = s3.listNextBatchOfObjects(objects);
        } while (objects.isTruncated());
        urls.remove(0);
        return urls;
    }

    public Optional<List<Note>> getHashtagNotes(String hashtag, String userId){
        List<Note> notes = jpaQueryFactory.select(qNote).from(qNote)
                .join(qNoteHashtag).on(qNote.id.eq(qNoteHashtag.note.id))
                .where(qNote.diary.id.in(
                        jpaQueryFactory.select(qUserDiary.diary.id).from(qUserDiary)
                        .where(qUserDiary.user.userId.eq(userId)
                        .or(qUserDiary.guestId.eq(userId)))
                )
                .and(qNoteHashtag.tagValue.eq(hashtag))).fetch();

        if(notes == null) return Optional.empty();
        return Optional.of(notes);
    }
}
