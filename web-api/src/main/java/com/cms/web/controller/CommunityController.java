package com.cms.web.controller;

import com.cms.common.common.ResponseCode;
import com.cms.common.common.ServerResponse;
import com.cms.common.vo.community.CommunityDetailsVo;
import com.cms.common.vo.community.CommunitySquareVo;
import com.cms.common.vo.community.MyCommunityVo;
import com.cms.web.feign.CommunityClient;
import com.cms.web.feign.OauthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * @author Creams
 */
@RestController
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityClient communityClient;


    @GetMapping("/squareList")
    public ServerResponse<List<CommunitySquareVo>> squareList(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {
        return communityClient.squareList(currentPage);
    }


    @GetMapping("/communityDetails")
    public ServerResponse<CommunityDetailsVo> details(@RequestParam("communityId") int communityId) {
        return communityClient.details(communityId);
    }

    @GetMapping("/myCommunity")
    public ServerResponse<List<MyCommunityVo>> myCommunity(Principal principal) {
        return communityClient.myCommunity(principal.getName());
    }
}
