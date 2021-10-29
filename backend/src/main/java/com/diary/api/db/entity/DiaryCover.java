package com.diary.api.db.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class DiaryCover extends BaseEntity {

    String CoverUrl;
    int CoverPrice;
    String CoverName;

}
