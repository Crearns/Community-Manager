package com.cms.common.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class News implements Serializable {
    private Integer id;
    private Date gmtCreate;
    private Date gmtModified;
    private Integer communityId;
    private String title;
    private Long authorId;
    private String content;
    private Byte visible;

    private static final long serialVersionUID = 1L;
}