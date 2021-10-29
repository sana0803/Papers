package com.diary.api.service;

import com.diary.api.db.entity.*;
import com.diary.api.db.repository.*;
import com.diary.api.request.NoteReq;
import com.diary.api.request.NoteStickerReq;
import com.diary.api.response.NoteRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    // 월별 일기 목록 조회
    @Override
    public List<NoteRes> getMonthNote(int month, Long diaryId){
        List<NoteRes> noteResList = new ArrayList<>();
        List<Note> notes = null;

        if(noteRepositorySupport.getMonthNote(month, diaryId).isPresent())
            notes = noteRepositorySupport.getMonthNote(month, diaryId).get();
        else return null;

        for(Note note : notes) {
            NoteRes noteRes = new NoteRes(note);
            noteRes.setNoteSticker(noteRepositorySupport.getNoteStickers(note.getId()).get());
            noteRes.setNoteEmotion(noteRepositorySupport.getNoteEmotions(note.getId()).get());
            noteRes.setNoteHashtag(noteRepositorySupport.getNoteHashtags(note.getId()).get());
            noteRes.setNoteMedia(noteRepositorySupport.getNoteMedias(note.getId()).get());
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
        noteRes.setNoteSticker(noteRepositorySupport.getNoteStickers(note.getId()).get());
        noteRes.setNoteEmotion(noteRepositorySupport.getNoteEmotions(note.getId()).get());
        noteRes.setNoteHashtag(noteRepositorySupport.getNoteHashtags(note.getId()).get());
        noteRes.setNoteMedia(noteRepositorySupport.getNoteMedias(note.getId()).get());
        return noteRes;
    }

    // 일기 작성
    @Override
    public NoteRes setNote(NoteReq noteReq){
        Note note = new Note();
        note.setNoteContent(noteReq.getNoteContent());
        note.setNoteDesign(noteRepositorySupport.getNoteDesign(noteReq.getDesignId()).get());
        note.setNoteLayout(noteRepositorySupport.getNoteLayout(noteReq.getLayoutId()).get());
        note.setNoteTitle(noteReq.getNoteTitle());
        note.setNoteContent(noteReq.getNoteContent());
        note.setNoteCreateDate(LocalDate.now());
        note.setNoteCreateTime(LocalTime.now());
        note.setDiary(diaryRepository.findById(noteReq.getDiaryId()).get());
        note.setFont(noteRepositorySupport.getFont(noteReq.getFontId()).get());
        note.setUser(userRepository.findByUserId(noteReq.getWriterId()).get());
        noteRepository.save(note);

        for(String hashtag : noteReq.getNoteHashtagList()){
            NoteHashtag noteHashtag = new NoteHashtag();
            noteHashtag.setNote(noteRepositorySupport.getNote(note.getId()).get());
            noteHashtag.setTagValue(hashtag);
            noteHashtagRepository.save(noteHashtag);
        }

        for(String media : noteReq.getNoteMediaList()){
            NoteMedia noteMedia = new NoteMedia();
            noteMedia.setNote(noteRepositorySupport.getNote(note.getId()).get());
            noteMedia.setMediaUrl(media);
            noteMedia.setMediaExtension(media.split("\\.")[media.split("\\.").length - 1]);
            noteMediaRepository.save(noteMedia);
        }

        for(NoteStickerReq sticker : noteReq.getStickerList()){
            NoteSticker noteSticker = new NoteSticker();
            noteSticker.setSticker(noteRepositorySupport.getSticker(sticker.getStickerId()).get());
            noteSticker.setNote(noteRepositorySupport.getNote(note.getId()).get());
            noteSticker.setTopPixel(sticker.getTopPixel());
            noteSticker.setLeftPixel(sticker.getLeftPixel());
            noteStickerRepository.save(noteSticker);
        }

        NoteRes noteRes = new NoteRes(note);
        noteRes.setNoteSticker(noteRepositorySupport.getNoteStickers(note.getId()).get());
        noteRes.setNoteEmotion(noteRepositorySupport.getNoteEmotions(note.getId()).get());
        noteRes.setNoteHashtag(noteRepositorySupport.getNoteHashtags(note.getId()).get());
        noteRes.setNoteMedia(noteRepositorySupport.getNoteMedias(note.getId()).get());
        return noteRes;
    }
}
