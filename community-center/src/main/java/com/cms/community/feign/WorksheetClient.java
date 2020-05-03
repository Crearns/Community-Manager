package com.cms.community.feign;

import com.cms.common.common.ServerResponse;
import com.cms.common.entity.Worksheet;
import com.cms.common.vo.worksheet.WorksheetVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Creams
 */

@FeignClient("sheet-service")
public interface WorksheetClient {

    @PostMapping("sheet/worksheetCommunity")
    ServerResponse<Worksheet> createWorksheet(@RequestParam("name") String name,
                                              @RequestParam("submitId") Long submitId,
                                              @RequestParam("content") String content,
                                              @RequestParam("catalog") Integer catalog,
                                              @RequestParam("remark") String remark);


    @GetMapping("sheet/worksheetInfo")
    ServerResponse<List<WorksheetVo>> worksheetInfo(@RequestParam("id") Long id);

    @GetMapping("sheet/verifyInfo")
    ServerResponse<List<WorksheetVo>> verifyInfo(@RequestParam("id") Long id);

    @GetMapping("sheet/communityVerifyList")
    ServerResponse<List<WorksheetVo>> communityVerify(@RequestParam("communityId") Integer communityId);

    @GetMapping("sheet/applyRecord")
    ServerResponse<Integer> applyRecord(@RequestParam("communityId") Integer communityId, @RequestParam("userId") Long userId);

    @PostMapping("sheet/worksheetParticipation")
    ServerResponse<Worksheet> participation(@RequestParam("name") String name,
                                            @RequestParam("submitId") Long submitId,
                                            @RequestParam("content") String content);

    @PutMapping("/sheet/worksheetState")
    ServerResponse worksheetState(@RequestParam("worksheetId") Integer worksheetId, @RequestParam("state") Integer state, @RequestParam("auditId") Long auditId, @RequestParam("remark") String remark);

    @GetMapping("sheet/worksheet")
    ServerResponse<Worksheet> worksheet(@RequestParam("id") Integer worksheetId);

    @PutMapping("worksheet")
    ServerResponse worksheet(@RequestParam("worksheet") Worksheet worksheet);

}
