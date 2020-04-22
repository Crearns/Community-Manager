package com.cms.workSheet.service;

import com.cms.common.entity.Worksheet;
import com.cms.common.query.WorksheetQuery;

import java.util.List;

/**
 * @author Creams
 */
public interface WorksheetService {
    int createWorksheet(Worksheet worksheet);

    List<Worksheet> query(WorksheetQuery worksheetQuery);
}
