package com.cms.common.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Activity implements Serializable {
    private Integer id;
    private Date gmtCreate;
    private Date gmtModified;
    private String title;
    private Integer communityId;
    private Date activityTime;
    private String content;

    private static final long serialVersionUID = 1L;
}