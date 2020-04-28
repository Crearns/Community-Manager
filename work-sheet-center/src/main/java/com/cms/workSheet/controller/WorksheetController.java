package com.cms.workSheet.controller;

import com.cms.common.common.ResponseCode;
import com.cms.common.common.ServerResponse;
import com.cms.common.entity.Community;
import com.cms.common.entity.Worksheet;
import com.cms.common.query.WorksheetQuery;
import com.cms.common.vo.worksheet.WorksheetInfoVo;
import com.cms.common.vo.worksheet.WorksheetVo;
import com.cms.workSheet.feign.CommunityClient;
import com.cms.workSheet.service.WorksheetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Creams
 */

@RestController
@Slf4j
public class WorksheetController {
    @Autowired
    private WorksheetService worksheetService;

    @Autowired
    private CommunityClient communityClient;

    @PostMapping("/worksheetCommunity")
    public ServerResponse<Worksheet> createWorksheet(@RequestParam("name") String name,
                                          @RequestParam("submitId") Long submitId,
                                          @RequestParam("content") String content,
                                          @RequestParam("catalog") Integer catalog,
                                          @RequestParam("remark") String remark) {

        List<Community> communities = communityClient.community(name).getData();

        if (catalog == 1 && communities.size() > 0) {
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
                .remark(remark)
                .catalog(catalog)
                .build();

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
        List<WorksheetInfoVo> worksheets = worksheetService.communityApply(worksheetQuery);

        List<WorksheetVo> res = new ArrayList<>();

        for (WorksheetInfoVo worksheet : worksheets) {
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
        List<WorksheetInfoVo> worksheets = worksheetService.communityApply(worksheetQuery);

        List<WorksheetVo> res = new ArrayList<>();

        for (WorksheetInfoVo worksheet : worksheets) {
            WorksheetVo worksheetVo = WorksheetVo.wrap(worksheet);
            res.add(worksheetVo);
        }

        return ServerResponse.createSuccessResponse(res);
    }

    @GetMapping("/communityVerifyList")
    public ServerResponse<List<WorksheetVo>> communityVerify(@RequestParam("communityId") Integer communityId) {
        List<Community> communities = communityClient.communityId(communityId).getData();

        if (communities.isEmpty()) {
            return ServerResponse.createFailureResponse(ResponseCode.NULL);
        }

        // select s.gmt_create, s.content, u.real_name from tb_worksheet s, tb_user u where state = 0 and name = "海风学社" and catalog = 3 and state = 0 and u.id=s.submit_user_id

        WorksheetQuery worksheetQuery = WorksheetQuery.builder()
                .name(communities.get(0).getName())
                .catalog(3)
                .state(0)
                .build();

        List<WorksheetInfoVo> communityApplyVos = worksheetService.communityApply(worksheetQuery);

        if (communityId == 1) {
            worksheetQuery = WorksheetQuery.builder()
                    .state(0)
                    .build();

            communityApplyVos.addAll(worksheetService.union(worksheetQuery));
        }

        if (communityApplyVos.isEmpty()) {
            return ServerResponse.createFailureResponse(ResponseCode.NULL);
        }

        List<WorksheetVo> res = new ArrayList<>();

        communityApplyVos.forEach((node) -> {
            res.add(WorksheetVo.wrap(node));
        });

        return ServerResponse.createSuccessResponse(res);
    }


    @GetMapping("applyRecord")
    public ServerResponse<Integer> applyRecord(@RequestParam("communityId") Integer communityId, @RequestParam("userId") Long userId) {
        return ServerResponse.createSuccessResponse(worksheetService.applyRecord(communityId, userId));
    }



    @PostMapping("/worksheetParticipation")
    public ServerResponse<Worksheet> Participation(@RequestParam("name") String name,
                                                     @RequestParam("submitId") Long submitId,
                                                     @RequestParam("content") String content) {
        Worksheet worksheet = Worksheet.builder()
                .name(name)
                .submitUserId(submitId)
                .content(content)
                .catalog(3).build();

        int res = worksheetService.createWorksheet(worksheet);

        if (res == 0) {
            return ServerResponse.createFailureResponse("Create worksheet fail");
        }

        return ServerResponse.createSuccessResponse(worksheet);
    }

    @PutMapping("/worksheetState")
    public ServerResponse worksheetState(@RequestParam("worksheetId") Integer worksheetId, @RequestParam("state") Integer state, @RequestParam("auditId") Long auditId, String remark) {
        return ServerResponse.createSuccessResponse(worksheetService.updateWorksheetState(worksheetId, state, remark, auditId));
    }

    @GetMapping("worksheet")
    public ServerResponse<Worksheet> worksheet(@RequestParam("id") Integer worksheetId) {
        WorksheetQuery worksheetQuery = WorksheetQuery.builder()
                .id(worksheetId).build();

        List<Worksheet> worksheets = worksheetService.query(worksheetQuery);
        return ServerResponse.createSuccessResponse(worksheets.get(0));
    }
}
