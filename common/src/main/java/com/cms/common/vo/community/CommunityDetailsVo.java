package com.cms.common.vo.community;

import lombok.Data;

/**
 * @author Creams
 */
@Data
public class CommunityDetailsVo {
    private Integer id;
    private String name;
    private String catalogName;
    private String historyNum;
    private String gmtCreate;
    private String president;
    private String description;
    private Integer memberCount;
}
