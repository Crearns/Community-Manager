package com.cms.common.vo.worksheet;

import lombok.Data;

import java.util.Date;

/**
 * @author Creams
 */

@Data
public class WorksheetInfoVo {
    private Integer id;
    private Date gmtCreate;
    private Date gmtModified;
    private String name;
    private String submitRealName;
    private String auditRealName;
    private Integer state;
    private String content;
    private Integer catalog;
    private String remark;
}
