package com.cms.workSheet.service.impl;

import com.cms.common.entity.Worksheet;
import com.cms.common.query.WorksheetQuery;
import com.cms.common.vo.worksheet.WorksheetInfoVo;
import com.cms.workSheet.dao.WorksheetDao;
import com.cms.workSheet.service.WorksheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Creams
 */

@Service
public class WorksheetServiceImpl implements WorksheetService {
    @Autowired
    private WorksheetDao worksheetDao;

    @Override
    public int createWorksheet(Worksheet worksheet) {
        return worksheetDao.insertSelective(worksheet);
    }

    @Override
    public List<Worksheet> query(WorksheetQuery worksheetQuery) {
        return worksheetDao.query(worksheetQuery);
    }

    @Override
    public int update(Worksheet worksheet) {
        return worksheetDao.updateByPrimaryKeySelective(worksheet);
    }

    @Override
    public List<WorksheetInfoVo> communityApply(WorksheetQuery worksheetQuery) {
        return worksheetDao.communitySheet(worksheetQuery);
    }

    @Override
    public List<WorksheetInfoVo> union(WorksheetQuery worksheetQuery) {
        return worksheetDao.union(worksheetQuery);
    }

    @Override
    public Integer applyRecord(Integer communityId, Long userId) {
        return worksheetDao.applyRecord(communityId, userId);
    }

    @Override
    public int updateWorksheetState(Integer id, Integer state, String remark, Long auditId) {
        Worksheet worksheet = Worksheet.builder()
                .id(id)
                .state(state)
                .remark(remark)
                .auditUserId(auditId)
                .build();

        return worksheetDao.updateByPrimaryKeySelective(worksheet);
    }


}
