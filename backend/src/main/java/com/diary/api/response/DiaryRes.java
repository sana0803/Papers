package com.diary.api.response;

import com.diary.api.db.entity.Diary;
import com.diary.api.db.entity.DiaryCover;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@ApiModel("DiaryResponse")
public class DiaryRes extends BaseResponseBody{
    @ApiModelProperty(name = "Diary Info")
    Long id;
    DiaryCover diaryCover;
    String diaryTitle;
    String ownerId;
    LocalDate diaryCreatedDate;
//    String guestId;
    List guest;

    public DiaryRes(Diary diary) {
        this.id = diary.getId();
        this.diaryCover = diary.getDiaryCover();
        this.diaryTitle = diary.getDiaryTitle();
        this.ownerId = diary.getUser().getUserId();
        this.diaryCreatedDate = diary.getDiaryCreatedDate();
    }
}
