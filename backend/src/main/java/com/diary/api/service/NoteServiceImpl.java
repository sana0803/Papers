package com.diary.api.service;

import com.diary.api.db.entity.*;
import com.diary.api.db.repository.*;
import com.diary.api.request.NoteEmotionReq;
import com.diary.api.request.NoteReq;
import com.diary.api.request.NoteStickerReq;
import com.diary.api.request.NotificationReq;
import com.diary.api.response.BaseResponseBody;
import com.diary.api.response.NoteRes;
import com.diary.api.response.NotificationDetailRes;
import com.diary.common.util.JwtTokenUtil;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.*;

import static org.reflections.Reflections.log;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    NoteRepositorySupport noteRepositorySupport;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    NoteHashtagRepository noteHashtagRepository;

    @Autowired
    NoteMediaRepository noteMediaRepository;

    @Autowired
    NoteStickerRepository noteStickerRepository;

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRepositorySupport userRepositorySupport;

    @Autowired
    EmotionRepository emotionRepository;

    @Autowired
    UserService userService;

    @Autowired
    EmotionLogRepository emotionLogRepository;

    @Autowired
    EmotionLogRepositorySupport emotionLogRepositorySupport;

    @Autowired
    UserDiaryRepository userDiaryRepository;

    @Autowired
    UserDiaryRepositorySupport userDiaryRepositorySupport;

    @Autowired
    NotificationService notificationService;

    @Autowired
    NotificationInfoRepository notificationInfoRepository;

    public List<NoteRes> getNoteList(String userId) {
        List<Note> notes = null;
        if(noteRepositorySupport.getNoteList(userId).isPresent())
            notes = noteRepositorySupport.getNoteList(userId).get();
        List<NoteRes> noteResList = new ArrayList<>();
        for(Note note : notes) {
            NoteRes noteRes = new NoteRes(note);
            noteRes.setNoteStickerList(noteRepositorySupport.getNoteStickers(note.getId()).get());
//            noteRes.setEmotionList(noteRepositorySupport.getNoteEmotions(note.getId()).get());
            noteRes.setNoteHashtagList(noteRepositorySupport.getNoteHashtags(note.getId()).get());
            noteRes.setNoteMediaList(noteRepositorySupport.getNoteMedias(note.getId()).get());
            noteResList.add(noteRes);
        }
        if(noteResList == null) return null;
        return noteResList;
    }

    // 월별 일기 목록 조회
    @Override
    public List<NoteRes> getMonthNote(int month, String userId){
        List<NoteRes> noteResList = new ArrayList<>();
        List<Note> notes = null;

        if(noteRepositorySupport.getMonthNote(month, userId).isPresent())
            notes = noteRepositorySupport.getMonthNote(month, userId).get();
        else return null;

        for(Note note : notes) {
            NoteRes noteRes = new NoteRes(note);
            noteRes.setNoteStickerList(noteRepositorySupport.getNoteStickers(note.getId()).get());
//            noteRes.setEmotionList(noteRepositorySupport.getNoteEmotions(note.getId()).get());
            noteRes.setNoteHashtagList(noteRepositorySupport.getNoteHashtags(note.getId()).get());
            noteRes.setNoteMediaList(noteRepositorySupport.getNoteMedias(note.getId()).get());
            noteResList.add(noteRes);
        }
        return noteResList;
    }

    // 일기 하루 조회
    @Override
    public NoteRes getNote(Long noteId) {
        Note note = null;
        if(noteRepositorySupport.getNote(noteId).isPresent())
             note = noteRepositorySupport.getNote(noteId).get();
        else return null;

        NoteRes noteRes = new NoteRes(note);
        noteRes.setNoteStickerList(noteRepositorySupport.getNoteStickers(note.getId()).get());
//        noteRes.setEmotionList(noteRepositorySupport.getNoteEmotions(note.getId()).get());
        noteRes.setNoteHashtagList(noteRepositorySupport.getNoteHashtags(note.getId()).get());
        noteRes.setNoteMediaList(noteRepositorySupport.getNoteMedias(note.getId()).get());
        return noteRes;
    }

    // 일기 작성
    @Override
    public NoteRes setNote(Long noteId, NoteReq noteReq){

        Note note = new Note();
        if(noteId != null)
            if(noteRepositorySupport.getNote(noteId).isPresent()) {
                note = noteRepositorySupport.getNote(noteId).get();
                note.setId(note.getId());
            }

        note.setNoteContent(noteReq.getNoteContent());
        note.setNoteDesign(noteRepositorySupport.getNoteDesign(noteReq.getDesignId()).get());
        note.setNoteLayout(noteRepositorySupport.getNoteLayout(noteReq.getLayoutId()).get());
        note.setNoteTitle(noteReq.getNoteTitle());
        note.setNoteContent(noteReq.getNoteContent());
//        note.setNoteCreateDate(LocalDate.now());
//        note.setNoteCreateTime(LocalTime.now());
        note.setDiary(diaryRepository.findById(noteReq.getDiaryId()).get());
        note.setFont(noteRepositorySupport.getFont(noteReq.getFontId()).get());
        note.setUser(userRepository.findByUserId(noteReq.getWriterId()).get());
        noteRepository.save(note);

        if(noteReq.getNoteHashtagList() != null) {
            noteRepositorySupport.deleteNoteHashtag(note.getId());
            for (String hashtag : noteReq.getNoteHashtagList()) {
                NoteHashtag noteHashtag = new NoteHashtag();
                noteHashtag.setNote(noteRepositorySupport.getNote(note.getId()).get());
                noteHashtag.setTagValue(hashtag);
                noteHashtagRepository.save(noteHashtag);
            }
        }

        noteRepositorySupport.deleteNoteMedia(note.getId());

        if(noteReq.getNoteMediaList() != null) {
            noteRepositorySupport.setNoteMedias(noteReq.getNoteMediaList(), noteReq.getWriterId(), note.getDiary().getId());
            for (MultipartFile multipartFile : noteReq.getNoteMediaList()) {
                if (multipartFile == null) continue;
                NoteMedia noteMedia = new NoteMedia();
                noteMedia.setNote(noteRepositorySupport.getNote(note.getId()).get());
                noteMedia.setMediaUrl("https://papers-bucket.s3.amazonaws.com/diary-file/"
                        + noteReq.getWriterId() + "/" + noteReq.getDiaryId() + "/" + multipartFile.getOriginalFilename());
                noteMedia.setMediaExtension(multipartFile.getOriginalFilename()
                        .substring(multipartFile.getOriginalFilename().lastIndexOf(".")));
                noteMediaRepository.save(noteMedia);
            }
        }

        if(noteReq.getNoteS3MediaList() != null) {
            for (String media : noteReq.getNoteS3MediaList()) {
                if (media.equals("null")) continue;
                NoteMedia noteMedia = new NoteMedia();
                noteMedia.setNote(noteRepositorySupport.getNote(note.getId()).get());
                noteMedia.setMediaUrl(media);
                noteMedia.setMediaExtension("." + media.split("\\.")[media.split("\\.").length - 1]);
                noteMediaRepository.save(noteMedia);
            }
        }

        if(noteReq.getStickerList() != null) {
            noteRepositorySupport.deleteNoteSticker(note.getId());
            for (NoteStickerReq sticker : noteReq.getStickerList()) {
                if (sticker == null) continue;
                NoteSticker noteSticker = new NoteSticker();
                noteSticker.setSticker(noteRepositorySupport.getSticker(sticker.getStickerId()).get());
                noteSticker.setNote(noteRepositorySupport.getNote(note.getId()).get());
                noteSticker.setTopPixel(sticker.getTopPixel());
                noteSticker.setLeftPixel(sticker.getLeftPixel());
                noteStickerRepository.save(noteSticker);
            }
        }

//        if(noteReq.getEmotionList() != null) {
//            noteRepositorySupport.deleteNoteEmotion(note.getId());
//            if (noteReq.getEmotionList().size() > 0) {
//                for (NoteEmotionReq noteEmotionReq : noteReq.getEmotionList()) {
//                    this.setNoteEmotion(noteEmotionReq);
//                }
//            }
//        }

        NoteRes noteRes = new NoteRes(note);
        noteRes.setNoteStickerList(noteRepositorySupport.getNoteStickers(note.getId()).get());
//        noteRes.setEmotionList(noteRepositorySupport.getNoteEmotions(note.getId()).get());
        noteRes.setNoteHashtagList(noteRepositorySupport.getNoteHashtags(note.getId()).get());
        noteRes.setNoteMediaList(noteRepositorySupport.getNoteMedias(note.getId()).get());

        // ---------------------------------------------------- 알림 전송
        long diaryId = noteReq.getDiaryId();
        String writerId = noteReq.getWriterId();
        List<UserDiary> userDiaryList = new ArrayList<>();
        List<String> guestList = new ArrayList<>();

        if (!userDiaryRepository.findAllByDiaryId(diaryId).isEmpty()) { // 다이어리 아이디에 해당하는 공유 정보 가져옴
            userDiaryList = userDiaryRepository.findAllByDiaryId(diaryId);
        }

        if (userDiaryRepositorySupport.isOwner(diaryId, writerId)) { // 일기 작성자가 다이어리 주인인 경우
            // guest들에게만 알림을 보낸다.
            userDiaryList.forEach(userDiary -> {
                guestList.add(userDiary.getGuestId());
            });
        } else if (userDiaryRepositorySupport.findByDiaryIdAndGuestId(diaryId, writerId) != null){ // 일기 작성자가 공유 받은 게스트 중 한 명일 경우
            UserDiary userDiary = userDiaryRepositorySupport.findByDiaryIdAndGuestId(diaryId, writerId);
            guestList.add(userDiary.getUser().getUserId()); // 일기장 주인 추가

            userDiaryList.forEach(userDiaryInfo -> {
                if (!userDiaryInfo.getGuestId().equals(writerId)) // 자신을 제외한 guest 들에게만 알림을 보낸다.
                    guestList.add(userDiaryInfo.getGuestId());
            });
        } else { // 공유한 일기장이 아닌 경우
            return noteRes;
        }

        User user = userRepository.findByUserId(writerId).get();
        String message = user.getUserNickname() + "님이 " + "\"" + noteReq.getNoteTitle() + "\"" + "라는 제목의 일기를 작성했습니다.";
        NotificationDetailRes notificationDetailRes = new NotificationDetailRes(message, user.getUserProfile());
        notificationService.publishToUsers(notificationDetailRes, guestList);

        NotificationInfo notificationInfo = notificationInfoRepository.findById((long)1).get();
        log.info("---note service 생성");
        for (String userId : guestList) {
            log.info("초대 받는 사람 : " + userId);
            User receiver = userService.getUserByUserId(userId);
            notificationService.createNotification(new NotificationReq(message, notificationInfo, user.getUserProfile(), receiver));
        }
        log.info("----------------");
        return noteRes;
    }

    // 일기 작성
    public NoteRes registNote(NoteReq noteReq){
        User user = userRepository.findByUserId(noteReq.getWriterId()).get();
        // 일기작성 마일리지 10점 적립
        userService.updateMileage(user, user.getUserMileage() + 10);
        return this.setNote(null, noteReq);
    }
    // 일기 수정
    public NoteRes updateNote(Long noteId, NoteReq noteReq) {
        return this.setNote(noteId, noteReq);
    }

    //  일기 삭제
    @OnDelete(action = OnDeleteAction.CASCADE)
    public boolean deleteNote(Long noteId) {
        try {
            if(noteRepositorySupport.getNote(noteId).isPresent())
                noteRepository.delete(noteRepositorySupport.getNote(noteId).get());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 일기들 일기장 옮기기
    public boolean changeDiaryNote(List<Long> notes, Long diaryId, Authentication authentication) {

        User user = JwtTokenUtil.getUser(authentication, userService);
//        try {
            for(Long noteId : notes) {
                Note note = noteRepositorySupport.getNote(noteId).get();
                if(user.getUserId() != note.getUser().getUserId()) return false;

                note.setDiary(diaryRepository.getOne(diaryId));
                noteRepository.save(note);
            }
            return true;
//        } catch (Exception e) {
//            return false;
//        }
    }

    public List<String> getImageFiles(String userId, Long diaryId){
        return noteRepositorySupport.getImageFiles(userId, diaryId);
    }

    // 일기에 감정표현 남기기
    public boolean setNoteEmotion(NoteEmotionReq noteEmotionReq){
        try {
            Emotion emotion = new Emotion();
            User user = userRepository.findByUserId(noteEmotionReq.getWriterId()).get();
            emotion.setEmotionInfo(noteRepositorySupport.getEmotionInfo(noteEmotionReq.getEmotionInfoId()).get());
            emotion.setNote(noteRepositorySupport.getNote(noteEmotionReq.getNoteId()).get());
            emotion.setUser(user);
            emotionRepository.save(emotion);

            List<EmotionLog> emotionLogList = emotionLogRepositorySupport.getEmotionLog(
                    noteEmotionReq.getNoteId(),
                    user.getUserId()).get();
            if(emotionLogList.size() == 0) {
                emotionLogRepository.save(new EmotionLog(user, noteRepositorySupport.getNote(noteEmotionReq.getNoteId()).get()));
                userService.updateMileage(user, user.getUserMileage() + 2);
            }

            long diaryId = noteEmotionReq.getDiaryId();
            String writerId = noteEmotionReq.getWriterId();
            List<UserDiary> userDiaryList = new ArrayList<>();
            List<String> guestList = new ArrayList<>();

            if (!userDiaryRepository.findAllByDiaryId(diaryId).isEmpty()) {
                userDiaryList = userDiaryRepository.findAllByDiaryId(diaryId);
            }

            if (userDiaryRepositorySupport.isOwner(diaryId, writerId)) {
                userDiaryList.forEach(userDiary -> {
                    guestList.add(userDiary.getGuestId());
                });
            } else if (userDiaryRepositorySupport.findByDiaryIdAndGuestId(diaryId, writerId) != null) {
                UserDiary userDiary = userDiaryRepositorySupport.findByDiaryIdAndGuestId(diaryId, writerId);
                guestList.add(userDiary.getUser().getUserId());

                userDiaryList.forEach(userDiaryInfo -> {
                    if (!userDiaryInfo.getGuestId().equals(writerId))
                        guestList.add(userDiaryInfo.getGuestId());
                });
            }
            Note note = noteRepositorySupport.getNote(noteEmotionReq.getNoteId()).get();
            String message = user.getUserNickname() + "님이" + " \'" + note.getNoteTitle() + "\" " + "에 감정을 표현했습니다.";
            NotificationDetailRes notificationDetailRes = new NotificationDetailRes(message, user.getUserProfile());
            notificationService.publishToUsers(notificationDetailRes, guestList);

            NotificationInfo notificationInfo = notificationInfoRepository.findById((long)2).get();
            log.info("--- note service 생성 : 감정표현");
            for (String userId : guestList) {
                log.info("감정 표현 알림 받는 사람 : " + userId);
                User receiver = userService.getUserByUserId(userId);
                notificationService.createNotification(new NotificationReq(message, notificationInfo, user.getUserProfile(), receiver));
            }
            log.info("----------------");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    // 감정표현 취소
    public boolean deleteNoteEmotion(NoteEmotionReq noteEmotionReq) {
        try {
            noteRepositorySupport.deleteNoteEmotionByUser(noteEmotionReq);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<NoteRes> getHashtagNotes(String hashtag, String userId) {
        List<Note> notes = null;
        if(noteRepositorySupport.getHashtagNotes(hashtag, userId).isPresent())
            notes = noteRepositorySupport.getHashtagNotes(hashtag, userId).get();
        List<NoteRes> noteResList = new ArrayList<>();
        for(Note note : notes) {
            NoteRes noteRes = new NoteRes(note);
            noteRes.setNoteStickerList(noteRepositorySupport.getNoteStickers(note.getId()).get());
//            noteRes.setEmotionList(noteRepositorySupport.getNoteEmotions(note.getId()).get());
            noteRes.setNoteHashtagList(noteRepositorySupport.getNoteHashtags(note.getId()).get());
            noteRes.setNoteMediaList(noteRepositorySupport.getNoteMedias(note.getId()).get());
            noteResList.add(noteRes);
        }
        if(noteResList == null) return null;
        return noteResList;
    }

    public List<String> getHashtagList(String userId){
        if(noteRepositorySupport.getHashtagList(userId).isPresent())
            return noteRepositorySupport.getHashtagList(userId).get();
        return null;
    }
}
