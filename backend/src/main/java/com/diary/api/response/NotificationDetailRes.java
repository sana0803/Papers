package com.diary.api.response;

import com.diary.api.db.entity.Album;
import com.diary.api.db.entity.AlbumContent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class NotificationDetailRes {
    private String message;
    private String imageUrl;
}
