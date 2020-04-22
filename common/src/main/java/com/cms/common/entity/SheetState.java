package com.cms.common.entity;

/**
 * @author Creams
 */
public enum SheetState {
    SUBMITTED(0, "已提交"),
    SUCCESS(1, "申请成功"),
    FAILURE(-1, "申请失败"),
    UNKNOWN(-1, "未知");
    ;

    private Integer code;
    private String desc;

    public static SheetState valueOf(int code) {
        SheetState[] sheetStates = SheetState.values();
        for (SheetState sheetState : sheetStates) {
            if (sheetState.code == code)
                return sheetState;
        }

        return UNKNOWN;
    }

    SheetState(Integer code, String desc) {
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
