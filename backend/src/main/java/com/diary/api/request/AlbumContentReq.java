package com.diary.api.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AlbumContentReq {
    List<String> albumContentUrl;
    List<String> albumContentText;
}
