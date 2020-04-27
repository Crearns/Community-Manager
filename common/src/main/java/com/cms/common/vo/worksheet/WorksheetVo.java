package com.cms.common.vo.worksheet;

import com.cms.common.entity.SheetState;
import com.cms.common.entity.Worksheet;
import com.cms.common.util.WorksheetUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author Creams
 */

@Data
@Slf4j
public class WorksheetVo {
    private Integer id;
    private String title;
    private Date create;
    private Date modified;
    private String submitName;
    private String auditName;
    private String state;
    private String content;
    private Integer catalog;
    private String remark;

    public static WorksheetVo wrap(Worksheet worksheet) {
        WorksheetVo worksheetVo = new WorksheetVo();
        worksheetVo.setId(worksheet.getId());
        worksheetVo.setContent(worksheet.getContent());
        worksheetVo.setCreate(worksheet.getGmtCreate());
        worksheetVo.setModified(worksheet.getGmtModified());
        worksheetVo.setState(SheetState.valueOf(worksheet.getState()).getDesc());
        worksheetVo.setTitle(WorksheetUtil.WorksheetNameGenerator(worksheet));
        worksheetVo.setRemark(worksheet.getRemark());
        worksheetVo.setCatalog(worksheet.getCatalog());
        return worksheetVo;
    }

    public static WorksheetVo wrap(WorksheetInfoVo worksheetInfoVo) {
        WorksheetVo worksheetVo = new WorksheetVo();
        worksheetVo.setId(worksheetInfoVo.getId());
        worksheetVo.setContent(worksheetInfoVo.getContent());
        worksheetVo.setCreate(worksheetInfoVo.getGmtCreate());
        worksheetVo.setModified(worksheetInfoVo.getGmtModified());
        worksheetVo.setSubmitName(worksheetInfoVo.getSubmitRealName());
        worksheetVo.setAuditName(worksheetInfoVo.getAuditRealName());
        worksheetVo.setState(SheetState.valueOf(worksheetInfoVo.getState()).getDesc());
        worksheetVo.setTitle(WorksheetUtil.WorksheetNameGenerator(worksheetInfoVo));
        worksheetVo.setRemark(worksheetInfoVo.getRemark());
        worksheetVo.setCatalog(worksheetInfoVo.getCatalog());
        return worksheetVo;
    }
}
