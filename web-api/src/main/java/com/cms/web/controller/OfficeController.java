package com.cms.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cms.common.common.ResponseCode;
import com.cms.common.common.ServerResponse;
import com.cms.common.entity.Community;
import com.cms.common.entity.SheetCatalog;
import com.cms.common.entity.Worksheet;
import com.cms.web.feign.CommunityClient;
import com.cms.web.feign.WorksheetClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Creams
 */

@RestController
@RequestMapping("office")
public class OfficeController {
    @Autowired
    private WorksheetClient worksheetClient;

    @Autowired
    private CommunityClient communityClient;

    @PutMapping("/worksheet")
    public ServerResponse worksheet(@RequestParam("worksheetId") Integer worksheetId,
                                    @RequestParam("catalog") Integer catalog,
                                    @RequestParam("agree") Integer agree,
                                    String reason) {
        int state = (agree == 1) ? 1 : -1;
        worksheetClient.worksheetState(worksheetId, state, reason);
        SheetCatalog sheetCatalog = SheetCatalog.valueOf(catalog);
        Worksheet worksheet = worksheetClient.worksheet(worksheetId).getData();


        switch (sheetCatalog){
            case COMMUNITY_FOUND:
                JSONObject object = JSON.parseObject(worksheet.getRemark());
                return communityClient.community(worksheet.getName(), object.getByte("communityCatalog"), object.getString("description"));
            case COMMUNITY_PARTICIPATION:
                Community community = communityClient.community(worksheet.getName()).getData().get(0);
                if (community == null) {
                    return ServerResponse.createFailureResponse("申请的社团已经不存在");
                }
                return communityClient.member(community.getId(), worksheet.getSubmitUserId());
            case COMMUNITY_ACTIVITY_APPLY:
            case COMMUNITY_CANCEL:
            case UNKNOWN:
            default:
                return ServerResponse.createFailureResponse(ResponseCode.NO_PERMISSION);
        }
    }
}
