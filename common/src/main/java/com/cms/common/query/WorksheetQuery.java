package com.cms.common.query;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

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
}
