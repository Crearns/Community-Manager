package com.cms.common.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Creams
 */

@Data
public class Message {
    private Long id;
    private Date gmtCreate;
    private Long receiverId;
    private String title;
    private String content;
    private Boolean read;
}
