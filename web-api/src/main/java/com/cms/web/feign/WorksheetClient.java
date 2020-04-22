package com.cms.web.feign;

import com.cms.common.common.ServerResponse;
import com.cms.common.entity.Worksheet;
import com.cms.common.vo.worksheet.WorksheetVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Creams
 */

@FeignClient("sheet-service")
public interface WorksheetClient {

    @PostMapping("sheet/worksheet")
    ServerResponse<Worksheet> createWorksheet(@RequestParam("name") String name,
                                              @RequestParam("submitId") Long submitId,
                                              @RequestParam("content") String content,
                                              @RequestParam("catalog") Integer catalog);


    @GetMapping("sheet/worksheetInfo")
    ServerResponse<List<WorksheetVo>> worksheetInfo(@RequestParam("id") Long id);
}
