package com.diary.api.db.repository;

import com.diary.api.db.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NoteRepositorySupport {

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

    public Optional<List<Note>> getMonthNote(int month, Long diaryId) {
        List<Note> notes = jpaQueryFactory.select(qNote).from(qNote)
                .where(qNote.noteCreateDate.month().eq(month)
                .and(qNote.diary.id.eq(diaryId))).fetch();
        if(notes == null) return Optional.empty();
        return Optional.ofNullable(notes);
    }

    public Optional<List<NoteSticker>> getNoteStickers(Long noteId){
        List<NoteSticker> stickers = jpaQueryFactory.select(qNoteSticker).from(qNoteSticker)
                .where(qNoteSticker.note.id.eq(noteId)).fetch();
        if(stickers == null) return Optional.empty();
        return Optional.ofNullable(stickers);
    }

    public Optional<List<Emotion>> getNoteEmotions(Long noteId){
        List<Emotion> emotions = jpaQueryFactory.select(qEmotion).from(qEmotion)
                .where(qEmotion.note.id.eq(noteId)).fetch();
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
}
