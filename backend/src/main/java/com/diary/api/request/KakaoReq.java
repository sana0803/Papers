package com.diary.api.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class KakaoReq {
    String id;
    String pwd;
    List<String> imageList;
}
