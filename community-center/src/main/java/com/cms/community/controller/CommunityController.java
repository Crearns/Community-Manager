package com.cms.community.controller;

import com.cms.common.common.ResponseCode;
import com.cms.common.common.ServerResponse;
import com.cms.common.entity.Community;
import com.cms.common.query.CommunityQuery;
import com.cms.common.vo.community.CommunityDetailsVo;
import com.cms.common.vo.community.CommunitySquareVo;
import com.cms.common.vo.community.MyCommunityVo;
import com.cms.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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

    @GetMapping("/userCommunity")
    public ServerResponse<List<MyCommunityVo>> userCommunity(String userId) {
        List<MyCommunityVo> res = communityService.getCommunityByUserId(userId);

        if (CollectionUtils.isEmpty(res)) {
            return ServerResponse.createFailureResponse(ResponseCode.NULL);
        }

        return ServerResponse.createSuccessResponse(res);
    }


    @GetMapping("/community")
    public ServerResponse<List<Community>> community(@RequestParam("name") String name) {
        CommunityQuery query = new CommunityQuery();
        query.setName(name);
        return ServerResponse.createSuccessResponse(communityService.query(query));
    }

}
