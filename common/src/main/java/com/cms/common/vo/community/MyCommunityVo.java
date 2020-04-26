package com.cms.common.vo.community;

import lombok.Data;

import java.util.Date;

/**
 * @author Creams
 */
@Data
public class MyCommunityVo {
    private Integer id;
    private String name;
    private String roleName;
    private Date createDate;
}
