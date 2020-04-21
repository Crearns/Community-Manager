package com.cms.common.entity;

/**
 * @author Creams
 */
public enum SheetCatalog {
    COMMUNITY_FOUND(1),
    COMMUNITY_CANCEL(2),
    COMMUNITY_PARTICIPATION(3),
    COMMUNITY_ACTIVITY_APPLY(4);

    private Integer code;

    SheetCatalog(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
