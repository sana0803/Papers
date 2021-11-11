package com.diary.api.response;


import com.diary.api.db.entity.Emotion;
import com.diary.api.db.entity.Note;
import com.diary.api.db.entity.NoteSticker;
import com.diary.api.db.entity.Sticker;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class NoteRes extends BaseResponseBody{
    Long noteId;
    Long diaryId;
    Long fontId;
    Long layoutId;
    Long designId;
    String writerId;
    String writerNickName;
    String noteTitle;
    String noteContent;
    LocalDate noteCreatedDate;
    LocalTime noteCreatedTime;
    List<String> noteHashtagList;
    List<String> noteMediaList;
    List<NoteSticker> noteStickerList;
//    List<Emotion> emotionList;
    EmotionStatusRes emotionStatusRes;
//    List<EmotionStatusRes> emotionStatusResList;

    public NoteRes(Note note) {
        this.noteId = note.getId();
        this.diaryId = note.getDiary().getId();
        this.fontId = note.getFont().getId();
        this.layoutId = note.getNoteLayout().getId();
        this.designId = note.getNoteDesign().getId();
        this.writerId = note.getUser().getUserId();
        this.writerNickName = note.getUser().getUserNickname();
        this.noteTitle = note.getNoteTitle();
        this.noteContent = note.getNoteContent();
        this.noteCreatedDate = note.getNoteCreateDate();
        this.noteCreatedTime = note.getNoteCreateTime();

        for (Emotion e : note.getEmotions()) {

        }
    }
}
