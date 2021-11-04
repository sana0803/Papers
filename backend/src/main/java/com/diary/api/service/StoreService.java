package com.diary.api.service;

import com.diary.api.db.entity.DiaryCover;
import com.diary.api.db.entity.Font;
import com.diary.api.db.entity.StickerPackage;
import com.diary.api.response.DiaryCoverRes;
import com.diary.api.response.FontRes;
import com.diary.api.response.StickerPackagesRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreService {
    public boolean purchaseStickerPackage(Long stickerPackageId, String userId);
    public List<StickerPackagesRes> getStickerList(String userId);
    public boolean purchaseFont(Long fontId, String userId);
    public List<FontRes> getFontList(String userId);
    public boolean purchaseDiaryCover(Long diaryCoverId, String userId);
    public List<DiaryCoverRes> getDiaryCoverList(String userId);
}
