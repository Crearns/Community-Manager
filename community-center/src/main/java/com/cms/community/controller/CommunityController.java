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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Queue;

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

    @GetMapping("/community-id")
    public ServerResponse<List<Community>> community(@RequestParam("communityId") Integer communityId) {
        CommunityQuery query = new CommunityQuery();
        query.setId(communityId);
        return ServerResponse.createSuccessResponse(communityService.query(query));
    }


    @GetMapping("/memberShip")
    public ServerResponse<Integer> memberShip(@RequestParam("userId") Long userId, @RequestParam("communityId") Integer communityId) {
        Integer role = communityService.memberShip(userId, communityId);
        if (role == null) {
            return ServerResponse.createFailureResponse(ResponseCode.NO_PERMISSION);
        }

        return ServerResponse.createSuccessResponse(role);
    }

    @PutMapping("/communityDescription")
    public ServerResponse communityDescription(@RequestParam("communityId") Integer communityId, @RequestParam("description") String description) {
        communityService.updateDescription(communityId, description);
        return ServerResponse.createSuccessResponse();
    }

    @PostMapping("/member")
    public ServerResponse member(@RequestParam("communityId") Integer communityId, @RequestParam("userId") Long userId) {
        return ServerResponse.createSuccessResponse(communityService.member(userId, communityId, 3));
    }

    @PostMapping("/community")
    public ServerResponse community(@RequestParam("name") String name,
                                    @RequestParam("catalog") Byte catalog,
                                    @RequestParam("description") String description) {
        CommunityQuery communityQuery = new CommunityQuery();
        communityQuery.setName(name);
        List<Community> communities = communityService.query(communityQuery);
        if (!communities.isEmpty()) {
            return ServerResponse.createFailureResponse(name + "社团已经存在");
        }

        return ServerResponse.createSuccessResponse(communityService.createCommunity(name, catalog, description));
    }
}
