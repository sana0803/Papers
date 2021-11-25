package com.diary.api.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    List<String> noteS3MediaList;
    List<MultipartFile> noteMediaList;
    List<String> noteHashtagList;
    List<NoteStickerReq> stickerList;
//    List<NoteEmotionReq> emotionList;
}
