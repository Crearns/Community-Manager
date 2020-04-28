package com.cms.common.vo.community;

import lombok.Data;

/**
 * @author Creams
 */

@Data
public class CommunityMemberInfoVo {
    private String realName;
    private Long userNum;
    private String roleName;
    private String college;
    private String major;
    private Integer grade;
    private Integer classNum;
}
