package com.diary.api.response;

import com.diary.api.db.entity.Diary;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@ApiModel("DiaryResponse")
public class DiaryRes {
    @ApiModelProperty(name = "Diary Info")
    Long id;
    String diaryTitle;
    String diaryDesc;
    String ownerId;
    LocalDate diaryCreatedDate;

    public static DiaryRes of(Diary diary) {
        DiaryRes diaryRes = new DiaryRes(
                diary.getId(),
                diary.getDiaryTitle(),
                diary.getDiaryDesc(),
                diary.getUser().getUserId(),
                diary.getDiaryCreatedDate()
        );
        return diaryRes;
    }

    public DiaryRes(Long id, String diaryTitle, String diaryDesc, String ownerId, LocalDate diaryCreatedDate) {
        this.id = id;
        this.diaryTitle = diaryTitle;
        this.diaryDesc = diaryDesc;
        this.ownerId = ownerId;
        this.diaryCreatedDate = diaryCreatedDate;
    }
}
