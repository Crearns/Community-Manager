package com.cms.web.controller;

import com.cms.common.common.ServerResponse;
import com.cms.common.vo.worksheet.WorksheetVo;
import com.cms.web.feign.WorksheetClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/communityVerifyList")
    public ServerResponse<List<WorksheetVo>> communityVerify(@RequestParam("communityId") Integer communityId) {
        return worksheetClient.communityVerify(communityId);
    }

    @GetMapping("applyRecord")
    public ServerResponse<Integer> applyRecord(@RequestParam("communityId") Integer communityId, @RequestParam("userId") Long userId) {
        return worksheetClient.applyRecord(communityId, userId);
    }



}
