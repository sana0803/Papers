package com.diary.api.controller;

import com.diary.api.db.entity.StickerPackage;
import com.diary.api.db.entity.User;
import com.diary.api.response.*;
import com.diary.api.service.StoreService;
import com.diary.api.service.UserService;
import com.diary.common.util.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    StoreService storeService;

    @Autowired
    UserService userService;

    @PutMapping("/sticker")
    @ApiOperation(value = "스티커 구매", notes = "스티커를 구매한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "스티커 구매 성공"),
            @ApiResponse(code = 401, message = "잘못된 토큰"),
            @ApiResponse(code = 500, message = "스티커 구매 중 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> purchaseStickerPackage(@RequestParam Long stickerPackageId, @ApiIgnore Authentication authentication) {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if(user == null) return ResponseEntity.status(401).body(BaseResponseBody.of(200, "잘못된 토큰"));
        if(storeService.purchaseStickerPackage(stickerPackageId, user.getUserId()))
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스티커 구매 성공"));
        return ResponseEntity.status(500).body(BaseResponseBody.of(500, "마일리지가 부족합니다."));
    }

    @GetMapping("/sticker")
    @ApiOperation(value = "상점 스티커 목록", notes = "상점에 스티커 목록을 반환한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "스티커 목록 반환 성공"),
            @ApiResponse(code = 401, message = "잘못된 토큰"),
            @ApiResponse(code = 500, message = "스티커 목록 반환 중 오류")
    })
    public ResponseEntity<List<StickerPackagesRes>> getStickerPackageList(@ApiIgnore Authentication authentication) {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if(user == null) return ResponseEntity.status(401).build();
        return ResponseEntity.status(200).body(storeService.getStickerList(user.getUserId()));
    }

    @PutMapping("/font")
    @ApiOperation(value = "폰트 구매", notes = "폰트를 구매한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "폰트 구매 성공"),
            @ApiResponse(code = 401, message = "잘못된 토큰"),
            @ApiResponse(code = 500, message = "폰트 구매 중 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> purchaseFont(@RequestParam Long fontId, @ApiIgnore Authentication authentication) {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if(user == null) return ResponseEntity.status(401).body(BaseResponseBody.of(200, "잘못된 토큰"));
        if(storeService.purchaseFont(fontId, user.getUserId()))
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "폰트 구매 성공"));
        return ResponseEntity.status(500).body(BaseResponseBody.of(500, "마일리지가 부족합니다."));
    }

    @GetMapping("/font")
    @ApiOperation(value = "상점 폰트 목록", notes = "상점에 폰트 목록을 반환한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "폰트 목록 반환 성공"),
            @ApiResponse(code = 401, message = "잘못된 토큰"),
            @ApiResponse(code = 500, message = "폰트 목록 반환 중 오류")
    })
    public ResponseEntity<List<FontRes>> getFontList(@ApiIgnore Authentication authentication) {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if(user == null) return ResponseEntity.status(401).build();
        return ResponseEntity.status(200).body(storeService.getFontList(user.getUserId()));
    }

    @PutMapping("/diary-cover")
    @ApiOperation(value = "다이어리 커버 구매", notes = "다이어리 커버를 구매한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "커버 구매 성공"),
            @ApiResponse(code = 401, message = "잘못된 토큰"),
            @ApiResponse(code = 500, message = "커버 구매 중 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> purchaseDiaryCover(@RequestParam Long diaryCoverId, @ApiIgnore Authentication authentication) {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if(user == null) return ResponseEntity.status(401).body(BaseResponseBody.of(200, "잘못된 토큰"));
        if(storeService.purchaseDiaryCover(diaryCoverId, user.getUserId()))
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "다이어리 커버 구매 성공"));
        return ResponseEntity.status(500).body(BaseResponseBody.of(500, "마일리지가 부족합니다."));
    }

    @GetMapping("/diary-cover")
    @ApiOperation(value = "상점 다이어리 커버 목록", notes = "상점에 커버 목록을 반환한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "커버 목록 반환 성공"),
            @ApiResponse(code = 401, message = "잘못된 토큰"),
            @ApiResponse(code = 500, message = "커버 목록 반환 중 오류")
    })
    public ResponseEntity<List<DiaryCoverRes>> getStickerPackageWithoutMine(@ApiIgnore Authentication authentication) {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if(user == null) return ResponseEntity.status(401).build();
        return ResponseEntity.status(200).body(storeService.getDiaryCoverList(user.getUserId()));
    }
}
