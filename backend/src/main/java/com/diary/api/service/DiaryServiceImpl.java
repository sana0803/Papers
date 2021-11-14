package com.diary.api.service;

import com.diary.api.db.entity.*;
import com.diary.api.db.repository.*;
import com.diary.api.request.DiaryInviteReq;
import com.diary.api.request.DiaryReq;
import com.diary.api.request.NotificationReq;
import com.diary.api.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.reflections.Reflections.log;

@Service("diaryService")
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    DiaryRepositorySupport diaryRepositorySupport;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    NoteRepositorySupport noteRepositorySupport;

    @Autowired
    UserDiaryRepository userDiaryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DiaryCoverRepository diaryCoverRepository;

    @Autowired
    NotificationService notificationService;

    @Autowired
    UserService userService;

    @Autowired
    NotificationInfoRepository notificationInfoRepository;
    // 일기장 생성
    @Override
    public DiaryRes createDiary(User user, DiaryReq diaryReq) {

        int i = 1;
        Long I = Long.valueOf(i);
        // 초기값 넣어주기
        DiaryCover cover = diaryCoverRepository.getOne(I);
//        DiaryCover coverId = diaryRepositorySupport.getDiaryCover(diaryReq.getCoverId()).get();
        String diaryTitle = diaryReq.getDiaryTitle();

        Diary diary = new Diary();
        diary.setDiaryCover(cover);
        diary.setDiaryTitle(diaryTitle);
        diary.setUser(user);
        diary.setDiaryCreatedDate(LocalDate.now());
        diaryRepository.save(diary);

//        UserDiary userDiary = new UserDiary();
//        userDiary.setDiary(diaryRepository.getOne(diary.getId()));
//        userDiary.setUser(user);
//        userDiaryRepository.save(userDiary);

        return new DiaryRes(diary);
    }

    //일기장 수정
    @Override
    public DiaryRes updateDiary(Long id, DiaryReq diaryReq) {
        Diary diary = diaryRepository.getOne(id);
        diary.setDiaryCover(diaryRepositorySupport.getDiaryCover(diaryReq.getCoverId()).get());
        diary.setDiaryTitle(diaryReq.getDiaryTitle());
        diaryRepository.save(diary);

        return new DiaryRes(diary);
    }

    // 내 일기장 전체 조회, 나에게 공유된 일기장도 조회
    @Override
    public List<DiaryRes> getDiaryList(User user) {
        List<DiaryRes> diaryResList = null;
        List<Diary> diaryList = diaryRepository.findAllByUser(user);
//        System.out.println(diaryList);

        // 나에게 되어있는 다이어리도 가져오기
        List<UserDiary> userDiaryList = userDiaryRepository.findByGuestId(user.getUserId());
//        System.out.println(userDiaryList);

        for (UserDiary userDiary : userDiaryList) {
//            System.out.println(userDiary);
            diaryList.add(userDiary.getDiary());
//            System.out.println("포문 끝에서" + diaryList);
        }

//        System.out.println("포문 나옴" + diaryList);
        // 다이어리 리턴값에맞춰서 변경
        if (diaryList.size() != 0) {
            diaryResList = convertToDiaryRes(diaryList, user);
        }
        return diaryResList;
    }

    // 일기장 한개 조회
    @Override
    public DiaryRes getDiary(Long id, User user) {

        List<NoteRes> noteResList = new ArrayList<>();

        Diary diary = diaryRepository.getOne(id);
        List<UserDiary> userDiaryList = userDiaryRepository.findAllByDiaryId(id);

        List<Note> notes = noteRepository.findAllByDiary(diary);

        List<UserSearchRes> guestList = new ArrayList<>();
        for (UserDiary userDiary : userDiaryList) {
            if (userRepository.findByUserId(userDiary.getGuestId()).isPresent()) {
                User guest = userRepository.findByUserId(userDiary.getGuestId()).get();
                // 공유다이어리에서 자신은 제외
                if (!guest.getUserId().equals(user.getUserId())) {
                    guestList.add(new UserSearchRes(guest));
                }
            }
        }
        DiaryRes diaryRes = new DiaryRes(diary);
        diaryRes.setGuest(guestList);

        for (Note note: notes) {
            NoteRes noteRes = new NoteRes(note);
            noteRes.setNoteStickerList(noteRepositorySupport.getNoteStickers(note.getId()).get());

            Map<String, Integer> emotionMap = new HashMap<>();
            emotionMap.put("좋아요" ,0);
            emotionMap.put("웃겨요" ,0);
            emotionMap.put("슬퍼요" ,0);
            boolean isPressLike, isPressFunny, isPressSad;
            isPressLike = isPressFunny = isPressSad = false;
            for (Emotion e : noteRepositorySupport.getNoteEmotions(note.getId()).get()) {
                String type = e.getEmotionInfo().getEmotionName();
                int val = emotionMap.get(type);
                emotionMap.put(type, val + 1);

                if (user.getUserId().equals(e.getUser().getUserId())) {
                    if (type.equals("좋아요")) {
                        isPressLike = true;
                    } else if (type.equals("웃겨요")) {
                        isPressFunny = true;
                    } else if (type.equals("슬퍼요")) {
                        isPressSad = true;
                    }
                }
            }
            noteRes.setEmotionStatusRes(EmotionStatusRes.of(emotionMap, isPressLike, isPressFunny, isPressSad));
//            noteRes.setEmotionList(noteRepositorySupport.getNoteEmotions(note.getId()).get());
            noteRes.setNoteHashtagList(noteRepositorySupport.getNoteHashtags(note.getId()).get());
            noteRes.setNoteMediaList(noteRepositorySupport.getNoteMedias(note.getId()).get());
            noteResList.add(noteRes);
        }

        diaryRes.setNote(noteResList);
//        return noteResList;
        return diaryRes;
    }


    // 일기장 삭제
    @Override
    public void deleteDiary(Long id) {
        diaryRepository.deleteById(id);
    }

    // 일기장을 필요정보만 리턴
    public List<DiaryRes> convertToDiaryRes(List<Diary> diaries, User user) {
        List<DiaryRes> diaryResList = new ArrayList<>();
        for (Diary diary : diaries) {
            // 공유다이어리인경우 guest 찾아서 닉네임으로 넣어주기
//            System.out.println(diary + "컨버트다이어리의 포문안에서");
            List<UserDiary> userDiaryList = new ArrayList<>();
            if (!userDiaryRepository.findAllByDiaryId(diary.getId()).isEmpty()) {
                userDiaryList = userDiaryRepository.findAllByDiaryId(diary.getId());
            }
            List<UserSearchRes> guestList = new ArrayList<>();
            for (UserDiary userDiary : userDiaryList) {
//                System.out.println(userDiary + "컨버트다이어리의 두번째포문안에서");
                if (userRepository.findByUserId(userDiary.getGuestId()).isPresent()) {
                    User guest = userRepository.findByUserId(userDiary.getGuestId()).get();
//                    System.out.println(guest + "게스트");
                    // 공유다이어리에서 자신은 제외
                    if (!guest.getUserId().equals(user.getUserId())) {
                        guestList.add(new UserSearchRes(guest));
                    }
                }
            }
            DiaryRes diaryRes = new DiaryRes(diary);
            diaryRes.setGuest(guestList);
            diaryResList.add(diaryRes);
        }
        return diaryResList;
    }

    //다이어리 초대
    @Override
    public boolean inviteDiary(User user, DiaryInviteReq diaryInviteReq) {
        List<String> guestList = diaryInviteReq.getInviteList();
        Diary diary = diaryRepository.getOne(diaryInviteReq.getDiaryId());

        for (String guestId : guestList) {
            UserDiary userDiary = new UserDiary();
            userDiary.setGuestId(guestId);
            userDiary.setAccepted(false);
            userDiary.setUser(user);
            userDiary.setDiary(diary);
            userDiaryRepository.save(userDiary);
        }

        // 알림 전송 ---------------------------------
        String message = user.getUserNickname() + "님이 " + diary.getDiaryTitle() + " 일기장에 회원님을 초대했습니다.";
        NotificationDetailRes notificationDetailRes = new NotificationDetailRes(message, user.getUserProfile());
        notificationService.publishToUsers(notificationDetailRes, guestList);


        NotificationInfo notificationInfo = notificationInfoRepository.findById((long)3).get();

        log.info("---diary service");
        for (String userId : guestList) {
            log.info("초대 받는 사람 : " + userId);
            User receiver = userService.getUserByUserId(userId);
            notificationService.createNotification(new NotificationReq(message, notificationInfo, user.getUserProfile(), receiver, diaryInviteReq.getDiaryId(), (long)0));
        }
        log.info("----------------");

        return true;
    }

    // 다이어리에 초대된 사람 삭제
    @Override
    public boolean disinviteDiary(Long diaryId, String userId) {
        List<UserDiary> userDiaryList = userDiaryRepository.findAllByDiaryId(diaryId);
        for (UserDiary userDiary : userDiaryList) {
            if (userDiary.getGuestId().equals(userId)) {
                userDiaryRepository.delete(userDiary);
                return true;
            }
        }
        return false;
    }
}
