package com.cms.common.query;

import lombok.Data;

import java.util.Date;

/**
 * @author Creams
 */
@Data
public class CommunityQuery {
    private Integer id;
    private String name;
    private Byte catalogId;
    private Integer historyNum;
    private String description;
}
