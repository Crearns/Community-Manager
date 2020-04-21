package com.cms.workSheet.service.impl;

import com.cms.common.entity.Worksheet;
import com.cms.workSheet.dao.WorksheetDao;
import com.cms.workSheet.service.WorksheetService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Creams
 */
public class WorksheetServiceImpl implements WorksheetService {
    @Autowired
    private WorksheetDao worksheetDao;

    @Override
    public int createWorksheet(Worksheet worksheet) {
        return worksheetDao.insertSelective(worksheet);
    }
}
