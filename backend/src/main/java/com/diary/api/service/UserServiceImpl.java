package com.diary.api.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.diary.api.db.entity.*;
import com.diary.api.db.repository.*;
import com.diary.api.request.NotificationReq;
import com.diary.api.request.UserLoginReq;
import com.diary.api.request.UserSignupReq;
import com.diary.api.request.UserUpdateReq;
import com.diary.api.response.*;
import com.diary.common.util.JwtTokenUtil;
import com.diary.common.util.S3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService{
    private final String DEFAULT_PROFILE_URL = "https://papers-bucket.s3.ap-northeast-2.amazonaws.com/profile/default-profile.png";

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDiaryRepository userDiaryRepository;

    @Autowired
    UserRepositorySupport userRepositorySupport;

    @Autowired
    StickerRepository stickerRepository;

    @Autowired
    FontRepository fontRepository;

    @Autowired
    UserStickerPackageRepository userStickerPackageRepository;

    @Autowired
    DiaryCoverRepository diaryCoverRepository;

    @Autowired
    DiaryCoverRepositorySupport diaryCoverRepositorySupport;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    NotificationInfoRepository notificationInfoRepository;

    @Autowired
    UserDiaryCoverRepository userDiaryCoverRepository;

    @Autowired
    UserFontRepository userFontRepository;

    @Autowired
    DiaryRepository diaryRepository;

    @Override
    public boolean signupUser(UserSignupReq userSignupReq) {
        if (userRepository.findByUserId(userSignupReq.getUserId()).isPresent()) {
            return false;
        }
        User user = new User();
        user.setUserId(userSignupReq.getUserId());
        user.setUserPwd(passwordEncoder.encode(userSignupReq.getUserPwd()));
        user.setUserNickname(userSignupReq.getUserNickname());
        user.setUserProfile(DEFAULT_PROFILE_URL);

        if (userRepository.save(user) == null)
            return false;

        // 유저 기본 다이어리 커버
//        DiaryCover basicDiaryCover = null;
        for (int i = 1; i <= 4; i++) {
            DiaryCover basicDiaryCover = diaryCoverRepository.findById((long)i).get();
            userDiaryCoverRepository.save(new UserDiaryCover(basicDiaryCover, user));
        }

        // 유저 기본 다이어리
        Diary basicDiary = new Diary();
        basicDiary.setDiaryCover(diaryCoverRepository.findById((long)1).get());
        basicDiary.setUser(user);
        basicDiary.setDiaryTitle("기본 일기장");
        diaryRepository.save(basicDiary);


        // 유저 기본 폰트
        Font basicFont = fontRepository.findById((long)1).get();
        userFontRepository.save(new UserFont(basicFont, user));
        return true;
    }

    @Override
    public boolean updateUser(User user, UserUpdateReq userUpdateReq) throws IOException {
        File uploadFile = S3Util.convert(userUpdateReq.getUserProfile())  // 파일 변환할 수 없으면 에러
                .orElseThrow(() -> new IllegalArgumentException("error: MultipartFile -> File convert fail"));
        String fileName = "profile/" + user.getUserId() + "/" + UUID.randomUUID() + uploadFile.getName();   // S3에 저장된 파일 이름
        String uploadImageUrl = S3Util.putS3(uploadFile, fileName); // s3로 업로드
        S3Util.removeNewFile(uploadFile);
        System.out.println(uploadImageUrl);

        user.setUserNickname(userUpdateReq.getUserNickname());
        user.setUserPwd(passwordEncoder.encode(userUpdateReq.getUserPwd()));
        user.setUserProfile(uploadImageUrl);

        if (userRepositorySupport.updateUser(user))
            return true;
        return false;
    }

    @Override
    public User getUserByUserId(String userId) {
        User user = null;
        if (userRepository.findByUserId(userId).isPresent())
            user = userRepository.findByUserId(userId).get();
        return user;
    }

    @Override
    public UserRes authenticate(UserLoginReq userLoginReq) {
        if (!userRepository.findByUserId(userLoginReq.getUserId()).isPresent())
            return null;

        User user = userRepository.findByUserId(userLoginReq.getUserId()).get();

        if (!passwordEncoder.matches(userLoginReq.getUserPwd(), user.getUserPwd())) {
            return null;
        }

        return new UserRes(user, JwtTokenUtil.getToken(user.getUserId()));
    }

    @Override
    public boolean updateMileage(User user, int amount) {
        return userRepositorySupport.updateMileage(user, amount);
    }

    @Override
    public List<StickerPackagesRes> getStickers(User user) {
        List<UserStickerPackage> stickerPackagesInfo = userStickerPackageRepository.getStickerPackageList(user.getUserId());
        List<StickerPackagesRes> stickersResList = new ArrayList<>();

        for (UserStickerPackage stickerInfo : stickerPackagesInfo) {
            stickersResList.add(new StickerPackagesRes(
                    stickerInfo.getStickerPackage(),
                    convertStickerRes(stickerRepository.getStickerList(stickerInfo.getStickerPackage().getId()))
            ));
        }
        return stickersResList;
    }

    @Override
    public List<Font> getFonts(User user) {
        List<Font> list = fontRepository.getFonts(user.getUserId());
        return list;
//        List<FontRes> fontResList = new ArrayList<>();
//        for (Font font : list) {
//            fontResList.add(new FontRes(font));
//        }
//        return fontResList;
    }

    @Override
    public List<DiaryCover> getDiaryCover(User user) {
        List<DiaryCover> list = diaryCoverRepositorySupport.getDiaryCover(user.getUserId());
        return list;
    }

    @Override
    public List<NotificationRes> getNotifications(User user) {
        List<Notification> notifications = userRepositorySupport.getNotifications(user.getUserId());
        List<NotificationRes> notificationResList = new ArrayList<>();
        for (Notification notification : notifications) {
            notificationResList.add(NotificationRes.of(notification));
        }
        return notificationResList;
    }

    @Override
    public boolean createNotification(User user, NotificationReq notificationReq) {
//        int notificationType = notificationReq.getNotificationInfoId();
//        switch (notificationType) {
//            case 1:
//                // 공유 일기 작성 알림
//                // 다이어리 id or 노트 id 필요
//                // 해당 다이어리에 공유된 모둔 유저한테 알림
//                break;
//            case 2:
//                // 감정 표현 알림
//                // 다이어리 id or 노트 id 필요
//                // 해당 다이어리에 공유된 모둔 유저한테 알림
//                break;
//            case 3:
//                // 공유 일기 초대 알림
//                // 다이어리 id 필요
//                // 해당 다이어리에 공유된 모둔 유저한테 알림
//                break;
//            default:
//        }
//        if (!notificationInfoRepository.findById((long)notificationReq.getNotificationInfoId()).isPresent()) {
//            return false;
//        }
//        Notification notification = new Notification();
//        notification.setNotificationContent(notificationReq.getNotificationContent());
//        notification.setNotificationInfo(notificationInfoRepository.findById((long)notificationReq.getNotificationInfoId()).get());
//        notification.setUser(user);
//        if (notificationRepository.save(notification) == null)
//            return false;
        return true;
    }

    @Override
    public boolean readNotification(User user, Long id) {
        return userRepositorySupport.readNotification(user.getUserId(), id);
    }

    @Override
    public List<UserSearchRes> getUsersLikeUserId(String userIdSubString) {
        List<UserSearchRes> UserSearchResList = new ArrayList<>();
        List<User> users = userRepositorySupport.getUsersLikeUserId(userIdSubString);
        for (User user : users) {
            UserSearchResList.add(new UserSearchRes(user));
        }
        return UserSearchResList;
    }

    public List<StickerRes> convertStickerRes (List<Sticker> stickers) {
        List<StickerRes> stickerRes = new ArrayList<>();
        for (Sticker sticker : stickers) {
            stickerRes.add(new StickerRes(sticker.getId(), sticker.getStickerUrl()));
        }
        return stickerRes;
    }

    // 유저 id로 유저 검색
    @Override
    public List<UserSearchRes> searchUserByUserID(String userId, String searchUserId) {
        List<UserSearchRes> UserSearchResList = new ArrayList<>();
        List<User> users = userRepository.findByUserIdContainingIgnoreCase(searchUserId);
        for (User user : users) {
            if (!userId.equals(user.getUserId()))
                UserSearchResList.add(new UserSearchRes(user));
        }
        return UserSearchResList;
    }

    // 다이어리 초대 수락
    @Override
    public boolean acceptInvite(User user, Long diaryId) {
        List<UserDiary> userDiaryList = userDiaryRepository.findAllByDiaryId(diaryId);
        for (UserDiary userDiary : userDiaryList) {
            if (userDiary.getGuestId().equals(user.getUserId())) {
                userDiary.setAccepted(true);
                userDiaryRepository.save(userDiary);
            }
        }
        return true;
    }
}
