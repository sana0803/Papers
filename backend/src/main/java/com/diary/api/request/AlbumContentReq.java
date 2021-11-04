package com.diary.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumContentReq {
    String albumContentText;
    int albumContentPage;
    String albumContentUrl;
}
