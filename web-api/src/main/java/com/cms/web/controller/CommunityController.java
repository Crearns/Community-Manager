package com.cms.web.controller;

import com.cms.common.common.ServerResponse;
import com.cms.common.entity.Catalog;
import com.cms.common.entity.Worksheet;
import com.cms.common.vo.community.CommunityDetailsVo;
import com.cms.common.vo.community.CommunitySquareVo;
import com.cms.common.vo.community.MyCommunityVo;
import com.cms.web.feign.CommunityClient;
import com.cms.web.feign.OauthClient;
import com.cms.web.feign.WorksheetClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private WorksheetClient worksheetClient;


    @GetMapping("/squareList")
    public ServerResponse<List<CommunitySquareVo>> squareList(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {
        return communityClient.squareList(currentPage);
    }


    @GetMapping("/communityDetails")
    public ServerResponse<CommunityDetailsVo> details(@RequestParam("communityId") int communityId) {
        return communityClient.details(communityId);
    }

    @GetMapping("/myCommunity")
    public ServerResponse<List<MyCommunityVo>> myCommunity(@RequestParam("userId") String userId) {
        return communityClient.myCommunity(userId); // todo : get current user
    }

    @GetMapping("/catalogList")
    public ServerResponse<List<Catalog>> catalogList() {
        return communityClient.catalogList();
    }


    @PostMapping("/communityFound")
    public ServerResponse<Worksheet> applicationMember(@RequestParam("name") String name,
                                                     @RequestParam("id") Long id, //user id
                                                     @RequestParam("catalog") Byte catalogId,
                                                     @RequestParam("description") String description) {
        ServerResponse<Catalog> catalog = communityClient.catalog(catalogId);

        if (catalog.getCode() != 0) {
            return ServerResponse.createFailureResponse("Catalog id is invalid");
        }

        StringBuilder content = new StringBuilder();

        content.append("\t大连海事大学社团创建申请书\n\n")
                .append("\t 社团名称:").append(name).append("\n")
                .append("\t 社团类别:").append(catalog.getData().getCatalogName()).append("\n")
                .append("\t 社团基本介绍: \n")
                .append("\t ").append(description);

        return worksheetClient.createWorksheet(name, id, content.toString(), 1);
    }


}
