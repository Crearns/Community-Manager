package com.cms.web.controller;

import com.cms.common.common.ServerResponse;
import com.cms.common.vo.worksheet.WorksheetVo;
import com.cms.web.feign.WorksheetClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Creams
 */

@RestController
@RequestMapping("worksheet")
public class WorksheetController {

    @Autowired
    private WorksheetClient worksheetClient;

    @GetMapping("/worksheetInfo")
    public ServerResponse<List<WorksheetVo>> worksheetInfo(@RequestParam("id") Long id) {
        return worksheetClient.worksheetInfo(id);
    }


    @GetMapping("/verifyInfo")
    public ServerResponse<List<WorksheetVo>> verifyInfo(@RequestParam("id") Long id) {
        return worksheetClient.verifyInfo(id);
    }


}
