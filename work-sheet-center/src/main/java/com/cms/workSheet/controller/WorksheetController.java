package com.cms.workSheet.controller;

import com.cms.common.common.ServerResponse;
import com.cms.common.entity.Community;
import com.cms.common.entity.Worksheet;
import com.cms.common.query.WorksheetQuery;
import com.cms.common.vo.worksheet.WorksheetVo;
import com.cms.workSheet.feign.CommunityClient;
import com.cms.workSheet.service.WorksheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Creams
 */

@RestController
public class WorksheetController {
    @Autowired
    private WorksheetService worksheetService;

    @Autowired
    private CommunityClient communityClient;

    @PostMapping("/worksheet")
    public ServerResponse<Worksheet> createWorksheet(@RequestParam("name") String name,
                                          @RequestParam("submitId") Long submitId,
                                          @RequestParam("content") String content,
                                          @RequestParam("catalog") Integer catalog) {

        List<Community> communities = communityClient.community(name).getData();

        if (communities.size() > 0) {
            return ServerResponse.createFailureResponse("社团:" + name + "已存在，请核实后重试");
        }

        WorksheetQuery worksheetQuery = WorksheetQuery.builder()
                .catalog(1)
                .name(name)
                .build();

        List<Worksheet> worksheets = worksheetService.query(worksheetQuery);

        for (Worksheet worksheet : worksheets) {
            if (worksheet.getState() == 0) {
                return ServerResponse.createFailureResponse("已有该社团创建的审核记录，请核实后重试");
            }
        }

        Worksheet worksheet = Worksheet.builder()
                .name(name)
                .submitUserId(submitId)
                .content(content)
                .catalog(catalog).build();

        int res = worksheetService.createWorksheet(worksheet);

        if (res == 0) {
            return ServerResponse.createFailureResponse("Create worksheet fail");
        }

        return ServerResponse.createSuccessResponse(worksheet);
    }


    @GetMapping("/worksheetInfo")
    public ServerResponse<List<WorksheetVo>> worksheetInfo(@RequestParam("id") Long id) {
        WorksheetQuery worksheetQuery = WorksheetQuery.builder()
                .submitUserId(id)
                .build();
        List<Worksheet> worksheets = worksheetService.query(worksheetQuery);

        List<WorksheetVo> res = new ArrayList<>();

        for (Worksheet worksheet : worksheets) {
            WorksheetVo worksheetVo = WorksheetVo.wrap(worksheet);
            res.add(worksheetVo);
        }

        return ServerResponse.createSuccessResponse(res);
    }

    @GetMapping("/verifyInfo")
    public ServerResponse<List<WorksheetVo>> verifyInfo(@RequestParam("id") Long id) {
        WorksheetQuery worksheetQuery = WorksheetQuery.builder()
                .auditUserId(id)
                .build();
        List<Worksheet> worksheets = worksheetService.query(worksheetQuery);

        List<WorksheetVo> res = new ArrayList<>();

        for (Worksheet worksheet : worksheets) {
            WorksheetVo worksheetVo = WorksheetVo.wrap(worksheet);
            res.add(worksheetVo);
        }

        return ServerResponse.createSuccessResponse(res);
    }
}
