package com.diary.api.controller;

import com.diary.api.db.entity.Diary;
import com.diary.api.db.entity.User;
import com.diary.api.db.entity.UserDiary;
import com.diary.api.db.repository.DiaryRepository;
import com.diary.api.db.repository.UserDiaryRepository;
import com.diary.api.request.DiaryInviteReq;
import com.diary.api.request.DiaryReq;
import com.diary.api.response.BaseResponseBody;
import com.diary.api.response.DiaryRes;
import com.diary.api.response.NoteRes;
import com.diary.api.service.DiaryService;
import com.diary.api.service.UserService;
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

    @Autowired
    UserDiaryRepository userDiaryRepository;

    @PostMapping()
    @ApiOperation(value = "일기장 만들기", notes = "일기장 만들기.")
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
    @ApiOperation(value = "내 일기장 전체 조회", notes = "내가 가진 일기장 전체 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기장 조회 성공"),
            @ApiResponse(code = 401, message = "인증"),
            @ApiResponse(code = 500, message = "일기장 조회 실패")
    })
    public ResponseEntity<List<DiaryRes>> getUserDiary(
            @ApiIgnore Authentication authentication
    ) {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(diaryService.getDiaryList(user));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "내 일기장 한개 조회", notes = "일기장 id로 일기장의 일기전체 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 조회 성공"),
            @ApiResponse(code = 401, message = "인증"),
            @ApiResponse(code = 500, message = "일기 조회 실패")
    })
    public ResponseEntity<List<NoteRes>> getOneDiary(
            @PathVariable Long id,
            @ApiIgnore Authentication authentication
    ) {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(diaryService.getDiary(id));
    }

    @PostMapping("/invite")
    @ApiOperation(value = "내 일기장에 다른 유저 초대요청", notes = "초대할 유저 id와 일기장 id로 추가")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기장 초대요청 성공"),
            @ApiResponse(code = 401, message = "인증오류"),
            @ApiResponse(code = 500, message = "일기장 초대요청 실패")
    })
    public ResponseEntity<? extends BaseResponseBody> inviteDiary(
            @ApiIgnore Authentication authentication,
            @RequestBody DiaryInviteReq diaryInviteReq
    ) {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponseBody.of(401, "존재하지 않는 회원입니다."));
        }

        String userId = user.getUserId();
        Diary diary = diaryRepository.getOne(diaryInviteReq.getDiaryId());
        if (!userId.equals(diary.getUser().getUserId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponseBody.of(401, "자신의 일기장만 초대할 수 있습니다."));
        }
        diaryService.inviteDiary(user, diaryInviteReq);
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(200, "초대요청이 성공적으로 이루어졌습니다."));
    }

    @DeleteMapping("/invite")
    @ApiOperation(value = "공유된 일기장에 멤버 퇴출", notes = "삭제할 유저 id와 일기장 id로 퇴출")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기장 초대요청 성공"),
            @ApiResponse(code = 401, message = "인증오류"),
            @ApiResponse(code = 500, message = "일기장 초대요청 실패")
    })
    public ResponseEntity<? extends BaseResponseBody> disinviteDiary(
            @ApiIgnore Authentication authentication,
            @RequestParam Long diaryId,
            @RequestParam String userId
    ) {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponseBody.of(401, "존재하지 않는 회원입니다."));
        }

        List<UserDiary> userDiaryList = userDiaryRepository.findAllByDiaryId(diaryId);
        for (UserDiary userDiary : userDiaryList ) {
            if (!userDiary.getUser().equals(user)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponseBody.of(401, "자신의 일기장 멤버만 삭제할 수 있습니다."));
            }
        }
        if (diaryService.disinviteDiary(diaryId, userId)) {
            return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(200, "멤버 삭제가 성공적으로 이루어졌습니다."));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(500, "해당 유저에게 공유된 일기장이 없습니다."));
    }
}
