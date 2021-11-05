//package com.diary.api.service;
//
//import com.diary.api.db.entity.Album;
//import com.diary.api.db.entity.AlbumContent;
//import com.diary.api.db.repository.AlbumContentRepository;
//import com.diary.api.db.repository.AlbumContentRepositorySupport;
//import com.diary.api.db.repository.AlbumRepository;
//import com.diary.api.db.repository.AlbumRepositorySupport;
//import com.diary.api.request.AlbumContentReq;
//import com.diary.api.response.AlbumRes;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class AlbumServiceImpl implements AlbumService{
//
//    @Autowired
//    AlbumRepository albumRepository;
//
//    @Autowired
//    AlbumContentRepository albumContentRepository;
//
//    @Autowired
//    AlbumRepositorySupport albumRepositorySupport;
//
//    @Autowired
//    AlbumContentRepositorySupport albumContentRepositorySupport;
//
//    public List<AlbumRes> getAlbumList(String userId){
//        List<AlbumRes> albumResList = new ArrayList<>();
//        if(albumRepositorySupport.getAlbumList(userId).isPresent()) {
//            List<Album> albums = albumRepositorySupport.getAlbumList(userId).get();
//            for(Album album : albums) {
//                AlbumRes albumRes = new AlbumRes();
//                albumRes.setAlbum(album);
//                albumRes.setAlbumContentList(albumContentRepositorySupport.getAlbumContentList(album.getId()).get());
//                albumResList.add(albumRes);
//            }
//            return albumResList;
//        }
//        else return null;
//    }
//
//    public AlbumRes getAlbum(Long albumId){
//        AlbumRes albumRes = new AlbumRes();
//        albumRes.setAlbum(albumRepository.findById(albumId).get());
//        albumRes.setAlbumContentList(albumContentRepositorySupport.getAlbumContentList(albumId).get());
//        return albumRes;
//    }
//
//    public List<AlbumContent> registAlbum(List<AlbumContentReq> albumReqs){
//        List<AlbumContent> albumContents = new ArrayList<>();
//        Album album = new Album();
//        for(AlbumContentReq albumReq : albumReqs){
//            AlbumContent albumContent = new AlbumContent(
//                    album,
//                    albumReq.getAlbumContentText(),
//                    albumReq.getAlbumContentPage(),
//                    albumReq.getAlbumContentUrl()
//            );
//            albumContentRepository.save(albumContent);
//            albumContents.add(albumContent);
//        }
//        albumRepository.save(album);
//        return albumContents;
//    }
//
//    public boolean deleteAlbum(Long albumId) {
//        try {
//            albumRepository.delete(albumRepository.findById(albumId).get());
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//}
