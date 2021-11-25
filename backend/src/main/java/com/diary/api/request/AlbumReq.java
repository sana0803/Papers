package com.diary.api.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AlbumReq {

    List<String> albumText;
    List<AlbumContentReq> albumContentReqs;

}
