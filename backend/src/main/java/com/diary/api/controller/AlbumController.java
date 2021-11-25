package com.diary.api.controller;

import com.diary.api.db.entity.User;
import com.diary.api.response.NoteRes;
import com.diary.api.service.NoteService;
import com.diary.api.service.UserService;
import com.diary.common.util.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/api/album")
public class AlbumController {

    @Autowired
    UserService userService;

    @Autowired
    NoteService noteService;

    @GetMapping("/{diaryId}")
    @ApiOperation(value = "다이어리 기준 사진파일 목록", notes = "해당 다이어리 기준 사진파일 목록을 반환한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "월별 일기 목록 반환 성공"),
            @ApiResponse(code = 401, message = "잘못된 토큰")
    })
    public ResponseEntity<List<String>> getMonthNotes(@PathVariable Long diaryId, @ApiIgnore Authentication authentication) {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if (user == null) return ResponseEntity.status(401).build();

        List<String> imageFiles = noteService.getImageFiles(user.getUserId(), diaryId);
        if(imageFiles == null) return ResponseEntity.status(500).build();
        return ResponseEntity.status(200).body(imageFiles);
    }
}
