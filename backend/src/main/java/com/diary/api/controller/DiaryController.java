package com.diary.api.controller;


import com.diary.api.db.entity.Diary;
import com.diary.api.db.entity.User;
import com.diary.api.request.DiaryReq;
import com.diary.api.request.NoteReq;
import com.diary.api.response.BaseResponseBody;
import com.diary.api.response.DiaryRes;
import com.diary.api.service.DiaryService;
import com.diary.api.service.UserService;
import com.diary.common.auth.PapersUserDetails;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.List;

@Api(value = "일기장 API", tags = {"Diary"})
@RestController
@RequestMapping("/api/diary")
public class DiaryController {

    @Autowired
    DiaryService diaryService;

    @Autowired
    UserService userService;

    @PostMapping()
    @ApiOperation(value = "일기장 만들기", notes = "일기장을 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기장 등록 성공"),
            @ApiResponse(code = 401, message = "인증 오류"),
            @ApiResponse(code = 500, message = "일기장 등록 실패")
    })
    public ResponseEntity<? extends BaseResponseBody> createDiary(
            @ApiIgnore Authentication authentication,
            @RequestBody DiaryReq diaryReq
    ) {
        PapersUserDetails userDetails = (PapersUserDetails)authentication.getDetails();
        User user = userService.getUserByUserId(userDetails.getUsername());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponseBody.of(401, "존재하지 않는 회원입니다."));
        }
        if (diaryReq == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(500, "내용이 존재하지 않거나 오류가 발생하였습니다."));
        }
        DiaryRes diaryRes = diaryService.createDiary(user, diaryReq);
        if (diaryRes == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(500, "내용이 존재하지 않거나 오류가 발생하였습니다."));
        }
        else {
            diaryRes.setStatusCode(200);
            diaryRes.setMessage("일기장이 정상적으로 생성되었습니다.");
            return ResponseEntity.status(HttpStatus.OK).body(diaryRes);
        }
    }

//    public ResponseEntity<DiaryRes> createDiary(
////            @ApiIgnore Authentication authentication,
//            @RequestBody DiaryReq diaryReq
//            ) throws IOException {
////        PapersUserDetails userDetails = (PapersUserDetails)authentication.getDetails();
////        User user = userService.getUserByUserId(userDetails.getUsername());
////        if (userDetails.getUser().getUserId() != null) {
////           return ResponseEntity.ok(diaryService.createDiary(user, diaryReq));
////        }
////        else {
////            return null;
////        }
//        return ResponseEntity.ok(diaryService.createDiary(diaryReq));
//    }

    @PutMapping("/{id}")
    @ApiOperation(value = "일기장 수정", notes = "일기장 id로 일기장 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기장 수정 성공"),
            @ApiResponse(code = 401, message = "일기장 수정 실패"),
            @ApiResponse(code = 500, message = "인증 오류")
    })
    public ResponseEntity<DiaryRes> updateDiary
            (@PathVariable Long id,
             @RequestBody DiaryReq diaryReq
            ) throws IOException {
        return ResponseEntity.ok(diaryService.updateDiary(id, diaryReq));
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "일기장 삭제", notes = "일기장 id로 일기장 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기장 삭제 성공"),
            @ApiResponse(code = 401, message = "일기장 삭제 실패"),
            @ApiResponse(code = 500, message = "인증 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> deleteDiary(@PathVariable Long id, @ApiIgnore Authentication authentication) {
        diaryService.deleteDiary(id);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Delete Success"));
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "내 일기장 전체 조회", notes = "유저 id로 일기장 전체 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기장 조회 성공"),
            @ApiResponse(code = 401, message = "일기장 조회 실패"),
            @ApiResponse(code = 500, message = "인증 오류")
    })
    public ResponseEntity<List<DiaryRes>> getUserDiary(@PathVariable String userId) {

        return ResponseEntity.status(200).body(diaryService.getDiaryList(userId));
    }
}
