package com.diary.api.response;

import com.diary.api.db.entity.Album;
import com.diary.api.db.entity.AlbumContent;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AlbumRes {
    private Album album;
    private List<AlbumContent> albumContentList;
}
