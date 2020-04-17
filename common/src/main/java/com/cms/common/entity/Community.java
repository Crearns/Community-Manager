package com.cms.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 
 */
@Data
public class Community implements Serializable {
    private Integer id;
    private Date gmtCreate;
    private Date gmtModified;
    private String name;
    private Byte catalogId;
    private Integer historyNum;
    private String description;
}