package com.cms.workSheet.service.impl;

import com.cms.common.entity.Worksheet;
import com.cms.common.query.WorksheetQuery;
import com.cms.common.util.PageBean;
import com.cms.common.vo.worksheet.WorksheetInfoVo;
import com.cms.workSheet.dao.WorksheetDao;
import com.cms.workSheet.service.WorksheetService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Creams
 */

@Service
@Slf4j
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
    public List<Worksheet> queryPage(WorksheetQuery worksheetQuery, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<Worksheet> list = worksheetDao.query(worksheetQuery);
        int count = list.size();
        PageBean<Worksheet> pageData = new PageBean<>(currentPage, pageSize, count);
        pageData.setItems(list);
        return pageData.getItems();
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
    public PageBean<WorksheetInfoVo> communityApplyPage(WorksheetQuery worksheetQuery, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<WorksheetInfoVo> list = worksheetDao.communitySheet(worksheetQuery);
        int count = worksheetDao.communitySheetCount(worksheetQuery);
        PageBean<WorksheetInfoVo> pageData = new PageBean<>(currentPage, pageSize, count);
        pageData.setItems(list);
        return pageData;
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
