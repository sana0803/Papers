package com.diary.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("DiaryRegisterRequest")
public class DiaryReq {
    @ApiModelProperty(name = "일기장 커버", example = "1")
    Long coverId;

    @ApiModelProperty(name = "일기장 제목", example = "싸피 일기장")
    String diaryTitle;

}
