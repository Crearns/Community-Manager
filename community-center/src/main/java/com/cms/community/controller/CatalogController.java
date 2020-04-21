package com.cms.community.controller;

import com.cms.common.common.ResponseCode;
import com.cms.common.common.ServerResponse;
import com.cms.common.entity.Catalog;
import com.cms.community.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Creams
 */

@RestController
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/catalogList")
    public ServerResponse<List<Catalog>> getAll() {
        List<Catalog> catalogs = catalogService.getAll();

        if (CollectionUtils.isEmpty(catalogs)) {
            return ServerResponse.createFailureResponse(ResponseCode.NULL);
        }

        return ServerResponse.createSuccessResponse(catalogs);
    }
}
