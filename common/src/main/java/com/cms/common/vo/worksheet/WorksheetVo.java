package com.cms.common.vo.worksheet;

import com.cms.common.entity.SheetState;
import com.cms.common.entity.Worksheet;
import com.cms.common.util.WorksheetUtil;
import lombok.Data;

import java.util.Date;

/**
 * @author Creams
 */

@Data
public class WorksheetVo {
    public String title;
    public Date create;
    public Date modified;
    public String state;
    public String content;
    public String remark;

    public static WorksheetVo wrap(Worksheet worksheet) {
        WorksheetVo worksheetVo = new WorksheetVo();
        worksheetVo.setContent(worksheet.getContent());
        worksheetVo.setCreate(worksheet.getGmtCreate());
        worksheetVo.setModified(worksheet.getGmtModified());
        worksheetVo.setState(SheetState.valueOf(worksheet.getState()).getDesc());
        worksheetVo.setTitle(WorksheetUtil.WorksheetNameGenerator(worksheet));
        worksheetVo.setRemark(worksheet.getRemark());
        return worksheetVo;
    }
}
