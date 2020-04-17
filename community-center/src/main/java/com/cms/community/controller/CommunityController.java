package com.cms.community.controller;

import com.cms.common.common.ServerResponse;
import com.cms.common.vo.community.CommunitySquareVo;
import com.cms.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Creams
 */

@RestController
public class CommunityController {
    @Autowired
    private CommunityService communityService;

    @GetMapping("squareList")
    private ServerResponse<List<CommunitySquareVo>> squareList(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {
        return ServerResponse.createSuccessResponse(communityService.getSquareList(currentPage, 20));
    }

}
