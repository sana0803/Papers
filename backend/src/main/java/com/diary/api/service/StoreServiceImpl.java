package com.diary.api.service;


import com.diary.api.db.entity.*;
import com.diary.api.db.repository.*;
import com.diary.api.response.DiaryCoverRes;
import com.diary.api.response.FontRes;
import com.diary.api.response.StickerPackagesRes;
import com.diary.api.response.StickerRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    UserService userService;

    @Autowired
    StickerPackageRepository stickerPackageRepository;

    @Autowired
    StoreRepositorySupport storeRepositorySupport;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserStickerPackageRepository userStickerPackageRepository;

    @Autowired
    FontRepository fontRepository;

    @Autowired
    UserFontRepository userFontRepository;

    @Autowired
    DiaryCoverRepository diaryCoverRepository;

    @Autowired
    UserDiaryCoverRepository userDiaryCoverRepository;


    // 스티커 구매
    public boolean purchaseStickerPackage(Long stickerPackageId, String userId){
        try {
            UserStickerPackage userStickerPackage = new UserStickerPackage();
            userStickerPackage.setStickerPackage(stickerPackageRepository.findById(stickerPackageId).get());
            userStickerPackage.setUser(userRepository.findByUserId(userId).get());
            if(storeRepositorySupport.getUserSticker(userId, stickerPackageId).isPresent()) return false;
            int amount = userRepository.findByUserId(userId).get().getUserMileage() - userStickerPackage.getStickerPackage().getStickerPackagePrice();
            if(amount < 0) return false;
            userStickerPackageRepository.save(userStickerPackage);
            userService.updateMileage(userRepository.findByUserId(userId).get(), amount);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 유저 소유 스티커 패키지 제외, 상점에 스티커 패키지 목록 가져오기
    public List<StickerPackagesRes> getStickerList(String userId){
        List<StickerPackagesRes> stickerPackagesResList = new ArrayList<>();
        Set<Long> ids = new HashSet<>();

        for(StickerPackagesRes stickerPackage : userService.getStickers(userRepository.findByUserId(userId).get()))
            ids.add(stickerPackage.getId());

        for(StickerPackage stickerPackage : stickerPackageRepository.findAll()) {

            StickerPackagesRes stickerPackagesRes = new StickerPackagesRes(
                    stickerPackage,
                    storeRepositorySupport.getStickerList(userId, stickerPackage.getId()).get()
            );
            if(ids.contains(stickerPackagesRes.getId()))
                stickerPackagesRes.setOwned(true);
            stickerPackagesResList.add(stickerPackagesRes);
        }
        return stickerPackagesResList;
    }

    // 폰트 구매
    public boolean purchaseFont(Long fontId, String userId){
        try {
            UserFont userFont = new UserFont();
            userFont.setFont(fontRepository.findById(fontId).get());
            userFont.setUser(userRepository.findByUserId(userId).get());
            if(storeRepositorySupport.getUserFont(userId, fontId).isPresent()) return false;
            int amount = userRepository.findByUserId(userId).get().getUserMileage() - userFont.getFont().getFontPrice();
            if(amount < 0) return false;
            userFontRepository.save(userFont);
            userService.updateMileage(userRepository.findByUserId(userId).get(), amount);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 유저 소유 폰트 제외, 상점에 폰트 목록 가져오기
    public List<FontRes> getFontList(String userId) {
        List<FontRes> fontResList = new ArrayList<>();
        Set<Long> userFontIds = new HashSet<>();

        for(Font font : userService.getFonts(userRepository.findByUserId(userId).get()))
            userFontIds.add(font.getId());

        for(Font font : fontRepository.findAll()) {
            FontRes fontRes = new FontRes(font);
            if(userFontIds.contains(fontRes.getId())) fontRes.setOwned(true);
            fontResList.add(fontRes);
        }
        return fontResList;
    }

    // 다이어리 커버 구매
    public boolean purchaseDiaryCover(Long diaryCoverId, String userId) {
        try {
            UserDiaryCover userDiaryCover = new UserDiaryCover();
            userDiaryCover.setDiaryCover(diaryCoverRepository.findById(diaryCoverId).get());
            userDiaryCover.setUser(userRepository.findByUserId(userId).get());
            if(storeRepositorySupport.getUserDiaryCover(userId, diaryCoverId).isPresent()) return false;
            int amount = userRepository.findByUserId(userId).get().getUserMileage() - userDiaryCover.getDiaryCover().getCoverPrice();
            if(amount < 0) return false;
            userDiaryCoverRepository.save(userDiaryCover);
            userService.updateMileage(userRepository.findByUserId(userId).get(), amount);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 유저 소유 다이어리 커버 제외, 상점에 다이어리 커버 목록 가져오기
    public List<DiaryCoverRes> getDiaryCoverList(String userId){
        List<DiaryCoverRes> diaryCoverResList = new ArrayList<>();
        Set<Long> userDiaryCoverIds = new HashSet<>();
        for(DiaryCover diaryCover : userService.getDiaryCover(userRepository.findByUserId(userId).get()))
            userDiaryCoverIds.add(diaryCover.getId());

        List<DiaryCover> diaryCoverList = diaryCoverRepository.findAll();
        for(int i = 4; i < diaryCoverList.size(); i++){
            DiaryCoverRes diaryCoverRes = new DiaryCoverRes(diaryCoverList.get(i));
            if(userDiaryCoverIds.contains(diaryCoverRes.getId())) diaryCoverRes.setOwned(true);
            diaryCoverResList.add(diaryCoverRes);
        }
        return diaryCoverResList;
    }
}
