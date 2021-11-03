package com.diary.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteEmotionReq {
    String writerId;
    Long emotionInfoId;
    Long noteId;
}
