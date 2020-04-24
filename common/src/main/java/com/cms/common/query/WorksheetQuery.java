package com.cms.common.query;

import lombok.Builder;
import lombok.Data;


/**
 * @author Creams
 */
@Data
@Builder
public class WorksheetQuery {
    private Integer id;
    private String name;
    private Long submitUserId;
    private Long auditUserId;
    private Integer state;
    private String content;
    private Integer catalog;
    private String remark;
}
