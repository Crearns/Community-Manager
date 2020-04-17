package com.cms.common.vo.community;

import lombok.Data;

import java.util.Date;

/**
 * @author Creams
 */
@Data
public class CommunitySquareVo {
    private Integer id;
    private String name;
    private String catalogName;
    private Date gmtCreate;
    private String president;
    private String historyNum;
}
