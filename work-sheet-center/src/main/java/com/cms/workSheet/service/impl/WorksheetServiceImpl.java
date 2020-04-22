package com.cms.workSheet.service.impl;

import com.cms.common.entity.Worksheet;
import com.cms.common.query.WorksheetQuery;
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


}
