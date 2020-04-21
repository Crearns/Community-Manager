package com.cms.community.dao;

import com.cms.common.entity.Catalog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogDao {
    int deleteByPrimaryKey(Byte id);

    int insert(Catalog record);

    int insertSelective(Catalog record);

    Catalog selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(Catalog record);

    int updateByPrimaryKey(Catalog record);

    List<Catalog> getAll();
}