package com.cms.community.service.impl;

import com.cms.common.entity.Catalog;
import com.cms.community.dao.CatalogDao;
import com.cms.community.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Creams
 */

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogDao catalogDao;

    @Override
    public List<Catalog> getAll() {
        return catalogDao.getAll();
    }

    @Override
    public Catalog selectById(Byte id) {
        return catalogDao.selectByPrimaryKey(id);
    }
}
