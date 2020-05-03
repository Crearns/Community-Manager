package com.cms.workSheet.service;

import com.cms.common.entity.Worksheet;
import com.cms.common.query.WorksheetQuery;
import com.cms.common.util.PageBean;
import com.cms.common.vo.worksheet.WorksheetInfoVo;

import java.util.List;

/**
 * @author Creams
 */
public interface WorksheetService {
    int createWorksheet(Worksheet worksheet);

    List<Worksheet> query(WorksheetQuery worksheetQuery);

    int update(Worksheet worksheet);

    List<WorksheetInfoVo> communityApply(WorksheetQuery worksheetQuery);

    List<WorksheetInfoVo> union(WorksheetQuery worksheetQuery);

    Integer applyRecord(Integer communityId, Long userId);

    int updateWorksheetState(Integer id, Integer state, String remark, Long auditId);

    List<Worksheet> queryPage(WorksheetQuery worksheetQuery, int currentPage, int pageSize);

    PageBean<WorksheetInfoVo> communityApplyPage(WorksheetQuery worksheetQuery, int currentPage, int pageSize);
}
