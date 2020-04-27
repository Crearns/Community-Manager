package com.cms.common.util;

import com.cms.common.entity.SheetCatalog;
import com.cms.common.entity.Worksheet;
import com.cms.common.vo.worksheet.WorksheetInfoVo;

/**
 * @author Creams
 */
public class WorksheetUtil {
    public static String WorksheetNameGenerator(Worksheet worksheet) {
        StringBuilder builder = new StringBuilder();
        builder.append("关于 ");
        builder.append(worksheet.getName())
                .append(" ").append(SheetCatalog.valueOf(worksheet.getCatalog()).getDesc())
                .append(" 的申请");

        return builder.toString();
    }

    public static String WorksheetNameGenerator(WorksheetInfoVo WorksheetInfoVo) {
        StringBuilder builder = new StringBuilder();
        builder.append("关于【")
                .append(WorksheetInfoVo.getSubmitRealName())
                .append("】提交的【")
                .append(WorksheetInfoVo.getName())
                .append("】").append(SheetCatalog.valueOf(WorksheetInfoVo.getCatalog()).getDesc())
                .append(" 的申请");

        return builder.toString();
    }
}
