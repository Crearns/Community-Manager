package com.cms.common.entity;

/**
 * @author Creams
 */
public enum SheetCatalog {
    COMMUNITY_FOUND(1, "创建社团"),
    COMMUNITY_CANCEL(2, "注销社团"),
    COMMUNITY_PARTICIPATION(3, "参加社团"),
    COMMUNITY_ACTIVITY_APPLY(4, "举办活动"),
    UNKNOWN(-1, "未知")
    ;
    private Integer code;
    private String desc;

    public static SheetCatalog valueOf(int code) {
        SheetCatalog[] catalogs = SheetCatalog.values();
        for (SheetCatalog catalog : catalogs) {
            if (catalog.code == code) return catalog;
        }
        return UNKNOWN;
    }

    SheetCatalog(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
