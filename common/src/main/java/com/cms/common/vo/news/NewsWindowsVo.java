package com.cms.common.vo.news;

import lombok.Data;

import java.util.Date;

/**
 * @author Creams
 */

@Data
public class NewsWindowsVo {
    private Integer id;
    private String title;
    private Date gmtCreate;
    private Date gmtModified;
    private String author;
    private String content;
}
