package com.cms.workSheet.controller;

import com.cms.common.common.ServerResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Creams
 */

@RestController
public class WorksheetController {

    @PostMapping("/worksheet")
    public ServerResponse createWorksheet() {
        return null;
    }

}
