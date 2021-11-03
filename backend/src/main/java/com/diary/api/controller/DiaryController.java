package com.diary.api.controller;


import com.diary.api.db.entity.Diary;
import com.diary.api.db.entity.User;
import com.diary.api.db.repository.DiaryRepository;
import com.diary.api.request.DiaryReq;
import com.diary.api.response.BaseResponseBody;
import com.diary.api.response.DiaryRes;
import com.diary.api.response.NoteRes;
import com.diary.api.service.DiaryService;
import com.diary.api.service.UserService;
import com.diary.common.auth.PapersUserDetails;
import com.diary.common.util.JwtTokenUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@Api(value = "일기장 API", tags = {"Diary"})
@RestController
@RequestMapping("/api/diary")
public class DiaryController {

    @Autowired
    DiaryService diaryService;

    @Autowired
    UserService userService;

    @Autowired
    DiaryRepository diaryRepository;

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
        if (diaryReq == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(500, "내용이 존재하지 않거나 오류가 발생하였습니다."));
        }

//        PapersUserDetails userDetails = (PapersUserDetails)authentication.getDetails();
//        User user = userService.getUserByUserId(userDetails.getUsername());
        User user = JwtTokenUtil.getUser(authentication, userService);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponseBody.of(401, "존재하지 않는 회원입니다."));
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

    @PutMapping("/{id}")
    @ApiOperation(value = "일기장 수정", notes = "일기장 id로 일기장 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기장 수정 성공"),
            @ApiResponse(code = 401, message = "인증 오류"),
            @ApiResponse(code = 500, message = "일기장 수정 실패")
    })
    public ResponseEntity<? extends BaseResponseBody> updateDiary (
            @PathVariable Long id,
            @ApiIgnore Authentication authentication,
            @RequestBody DiaryReq diaryReq
    ) {
        if (diaryReq == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(500, "내용이 존재하지 않거나 오류가 발생하였습니다."));
        }

//        PapersUserDetails userDetails = (PapersUserDetails)authentication.getDetails();
//        User user = userService.getUserByUserId(userDetails.getUsername());
        User user = JwtTokenUtil.getUser(authentication, userService);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponseBody.of(401, "존재하지 않는 회원입니다."));
        }

        Optional<Diary> diary = diaryRepository.findById(id);

        if (user != diary.get().getUser()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponseBody.of(401, "자신의 일기장만 수정할 수 있습니다."));
        }

        DiaryRes diaryRes = diaryService.updateDiary(id, diaryReq);
        if (diaryRes == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(500, "내용이 존재하지 않거나 오류가 발생하였습니다."));
        }
        else {
            diaryRes.setStatusCode(200);
            diaryRes.setMessage("일기장이 정상적으로 수정되었습니다.");
            return ResponseEntity.status(HttpStatus.OK).body(diaryRes);
        }

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "일기장 삭제", notes = "일기장 id로 일기장 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기장 삭제 성공"),
            @ApiResponse(code = 401, message = "인증 오류"),
            @ApiResponse(code = 500, message = "일기장 삭제 실패")
    })
    public ResponseEntity<? extends BaseResponseBody> deleteDiary(
            @PathVariable Long id,
            @ApiIgnore Authentication authentication
    ) {
//        PapersUserDetails userDetails = (PapersUserDetails)authentication.getDetails();
//        User user = userService.getUserByUserId(userDetails.getUsername());

        User user = JwtTokenUtil.getUser(authentication, userService);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponseBody.of(401, "존재하지 않는 회원입니다."));
        }

        Optional<Diary> diary = diaryRepository.findById(id);
        if (user != diary.get().getUser()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponseBody.of(401, "자신의 일기장만 삭제할 수 있습니다."));
        }

        diaryService.deleteDiary(id);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Delete Success"));
    }

    @GetMapping()
    @ApiOperation(value = "내 일기장 전체 조회", notes = "유저 id로 일기장 전체 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기장 조회 성공"),
            @ApiResponse(code = 401, message = "인증"),
            @ApiResponse(code = 500, message = "일기장 조회 실패")
    })
    public ResponseEntity<List<DiaryRes>> getUserDiary(
            @ApiIgnore Authentication authentication
    ) {
//        PapersUserDetails userDetails = (PapersUserDetails)authentication.getDetails();
//        User user = userService.getUserByUserId(userDetails.getUsername());
        User user = JwtTokenUtil.getUser(authentication, userService);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(diaryService.getDiaryList(user.getUserId()));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "내 일기장 한개 조회", notes = "유저 id로 일기장 한개 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 조회 성공"),
            @ApiResponse(code = 401, message = "인증"),
            @ApiResponse(code = 500, message = "일기 조회 실패")
    })
    public ResponseEntity<List<NoteRes>> getOneDiary(
            @PathVariable Long id,
            @ApiIgnore Authentication authentication
    ) {
//        PapersUserDetails userDetails = (PapersUserDetails)authentication.getDetails();
//        User user = userService.getUserByUserId(userDetails.getUsername());

        User user = JwtTokenUtil.getUser(authentication, userService);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(diaryService.getDiary(id));
    }
}
