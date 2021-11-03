package com.diary.api.controller;

import com.diary.api.request.NoteReq;
import com.diary.api.response.BaseResponseBody;
import com.diary.api.response.NoteRes;
import com.diary.api.service.NoteService;
import com.diary.common.auth.PapersUserDetails;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/api/note")
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping("/{month}")
    @ApiOperation(value = "월별 일기 목록", notes = "해당 다이어리의 지정 월별 목록을 반환한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "월별 일기 목록 반환 성공")
    })
    public ResponseEntity<List<NoteRes>> getMonthNotes(@PathVariable int month, @ApiParam Long diaryId) {
        List<NoteRes> noteResList = noteService.getMonthNote(month, diaryId);
        if(noteResList == null) return ResponseEntity.status(500).build();
        return ResponseEntity.status(200).body(noteResList);
    }

    @GetMapping()
    @ApiOperation(value = "날짜별 일기 조회", notes = "해당 날짜의 일기를 반환한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 반환 성공")
    })
    public ResponseEntity<? extends BaseResponseBody> getNote(@ApiParam Long noteId) {
        NoteRes noteRes = noteService.getNote(noteId);
        if(noteRes == null) return ResponseEntity.status(500).body(BaseResponseBody.of(500, "존재하지 않거나 오류가 발생하였습니다."));
        return ResponseEntity.status(200).body(noteRes);
    }

    @PostMapping()
    @ApiOperation(value = "일기 작성", notes = "일기 작성")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 저장 성공"),
            @ApiResponse(code = 500, message = "일기 저장 오류발생")
    })
    public ResponseEntity<? extends BaseResponseBody> setNote(@ApiIgnore Authentication authentication, @RequestBody NoteReq noteReq) {
        PapersUserDetails userDetails = (PapersUserDetails)authentication.getDetails();
        noteReq.setWriterId(userDetails.getUser().getUserId());
        NoteRes noteRes = noteService.registNote(noteReq);
        if(noteRes == null) return ResponseEntity.status(500).body(BaseResponseBody.of(500, "존재하지 않거나 오류가 발생하였습니다."));
        return ResponseEntity.status(200).body(noteRes);
    }

    @PutMapping("/{noteId}")
    @ApiOperation(value = "일기 수정", notes = "일기 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 수정 성공"),
            @ApiResponse(code = 401, message = "일기 수정 권한 없음"),
            @ApiResponse(code = 500, message = "일기 수정 오류발생")
    })
    public ResponseEntity<? extends BaseResponseBody> updateNote(@ApiIgnore Authentication authentication, @PathVariable Long noteId, @RequestBody NoteReq noteReq) {
        PapersUserDetails userDetails = (PapersUserDetails)authentication.getDetails();
        NoteRes noteRes = noteService.getNote(noteId);
        if(!userDetails.getUser().getUserId().equals(noteRes.getWriterId()))
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "작성자가 아니어서 수정할 수 없습니다."));
        noteRes = noteService.updateNote(noteId, noteReq);
        if(noteRes == null) return ResponseEntity.status(500).body(BaseResponseBody.of(500, "존재하지 않거나 오류가 발생하였습니다."));
        return ResponseEntity.status(200).body(noteRes);
    }

    @DeleteMapping("/{noteId}")
    @ApiOperation(value = "일기 삭제", notes = "일기 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 삭제 성공"),
            @ApiResponse(code = 401, message = "일기 삭제 권한 없음"),
            @ApiResponse(code = 500, message = "일기 삭제 오류발생")
    })
    public ResponseEntity<? extends BaseResponseBody> deleteNote(@ApiIgnore Authentication authentication, @PathVariable Long noteId) {
        PapersUserDetails userDetails = (PapersUserDetails)authentication.getDetails();
        NoteRes noteRes = noteService.getNote(noteId);
        if(!userDetails.getUser().getUserId().equals(noteRes.getWriterId()))
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "작성자가 아니어서 삭제할 수 없습니다."));
        if(!noteService.deleteNote(noteId)) return ResponseEntity.status(500).body(BaseResponseBody.of(500, "존재하지 않거나 오류가 발생하였습니다."));
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "삭제 완료"));
    }

    @PutMapping("/change-diary")
    @ApiOperation(value = "일기들 일기장 옮기기", notes = "일기들 일기장 옮기기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기장 옮기기 성공"),
            @ApiResponse(code = 500, message = "일기장 옮기는 중 오류발생")
    })
    public ResponseEntity<? extends BaseResponseBody> changeDiaryNote(@ApiIgnore Authentication authentication, @RequestBody List<Long> notes, @RequestBody Long diaryId) {
        if(!noteService.changeDiaryNote(notes, diaryId, authentication)) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(500, "존재하지 않거나 오류가 발생하였습니다."));
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "삭제 완료"));
    }

    @GetMapping("/files")
    @ApiOperation(value = "클라우드에서 사진파일 목록 가져오기", notes = "클라우드에서 사진파일 목록 가져오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사진파일 가져오기 성공"),
            @ApiResponse(code = 500, message = "사진파일 가져오는 중 오류발생")
    })
    public ResponseEntity<List<String>> getImageFiles(@ApiIgnore Authentication authentication, @ApiParam Long diaryId) {
        PapersUserDetails userDetails = (PapersUserDetails)authentication.getDetails();
        noteService.getImageFiles(userDetails.getUser().getUserId(), diaryId);
        return ResponseEntity.status(200).body(noteService.getImageFiles(userDetails.getUser().getUserId(), diaryId));
    }

//    @PostMapping("/files")
//    @ApiOperation(value = "클라우드에서 사진파일 목록 가져오기", notes = "클라우드에서 사진파일 목록 가져오기")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "사진파일 가져오기 성공"),
//            @ApiResponse(code = 500, message = "사진파일 가져오는 중 오류발생")
//    })
//    public ResponseEntity<List<String>> setImageFiles(@ApiIgnore Authentication authentication, @RequestBody List<MultipartFile> files) {
//        PapersUserDetails userDetails = (PapersUserDetails)authentication.getDetails();
//        noteService.getImageFiles(userDetails.getUser().getUserId(), diaryId);
//        return ResponseEntity.status(200).body(noteService.getImageFiles(userDetails.getUser().getUserId(), diaryId));
//    }

}
