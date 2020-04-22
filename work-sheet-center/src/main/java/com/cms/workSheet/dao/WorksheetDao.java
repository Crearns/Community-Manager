package com.cms.workSheet.dao;

import com.cms.common.entity.Worksheet;
import com.cms.common.query.WorksheetQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WorksheetDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Worksheet record);

    int insertSelective(Worksheet record);

    Worksheet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Worksheet record);

    int updateByPrimaryKey(Worksheet record);

    List<Worksheet> query(WorksheetQuery worksheetQuery);
}