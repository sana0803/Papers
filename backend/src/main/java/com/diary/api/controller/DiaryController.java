package com.diary.api.controller;


import com.diary.api.request.DiaryReq;
import com.diary.api.response.DiaryRes;
import com.diary.api.service.DiaryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Api(value = "일기장 API", tags = {"Diary"})
@RestController
@RequestMapping("/api/v1/diary")
public class DiaryController {

    @Autowired
    DiaryService diaryService;

    @PostMapping()
    @ApiOperation(value = "일기장 만들기", notes = "일기장을 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기장 등록 성공")
    })
    public ResponseEntity<DiaryRes> createDiary(
//            @RequestParam Long coverId,
            @RequestParam String diaryTitle,
            @RequestParam String diaryDesc,
            @RequestParam String owner
            ) throws IOException {
        return ResponseEntity.ok(diaryService.createDiary(DiaryReq.of(diaryTitle, diaryDesc, owner)));
    }

}
