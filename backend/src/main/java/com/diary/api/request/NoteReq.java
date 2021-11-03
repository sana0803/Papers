package com.diary.api.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NoteReq {
    Long diaryId;
    Long fontId;
    Long layoutId;
    Long designId;
    String writerId;
    String noteTitle;
    String noteContent;
    List<String> noteMediaList;
    List<String> noteHashtagList;
    List<NoteStickerReq> stickerList;
    List<NoteEmotionReq> emotionList;
}
