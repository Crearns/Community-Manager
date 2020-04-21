package com.cms.common.entity;

/**
 * @author Creams
 */
public enum SheetState {
    SUBMITTED(0),
    AUDITING(1),
    TO_BE_REVISE(2),
    SUCCESS(3),
    FAILURE(-1),
    ;

    private Integer code;

    SheetState(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
