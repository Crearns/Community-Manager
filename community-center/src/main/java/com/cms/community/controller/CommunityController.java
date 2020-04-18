package com.cms.community.controller;

import com.cms.common.common.ResponseCode;
import com.cms.common.common.ServerResponse;
import com.cms.common.entity.Community;
import com.cms.common.vo.community.CommunityDetailsVo;
import com.cms.common.vo.community.CommunitySquareVo;
import com.cms.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Creams
 */

@RestController
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @GetMapping("/squareList")
    public ServerResponse<List<CommunitySquareVo>> squareList(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {
        return ServerResponse.createSuccessResponse(communityService.getSquareList(currentPage, 20));
    }

    @GetMapping("/communityDetails")
    public ServerResponse<CommunityDetailsVo> details(@RequestParam("communityId") int communityId) {
        CommunityDetailsVo details = communityService.searchDetailsInfo(communityId);
        if (details != null) {
            return ServerResponse.createSuccessResponse(details);
        }
        return ServerResponse.createFailureResponse(ResponseCode.NULL);
    }
}
