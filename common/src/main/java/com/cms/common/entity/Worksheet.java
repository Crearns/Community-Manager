package com.cms.common.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class Worksheet implements Serializable {
    private Integer id;
    private Date gmtCreate;
    private Date gmtModified;
    private String name;
    private Long submitUserId;
    private Long auditUserId;
    private Integer state;
    private String content;
    private Integer catalog;
}