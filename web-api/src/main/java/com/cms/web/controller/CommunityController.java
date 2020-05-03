package com.cms.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.cms.common.common.ServerResponse;
import com.cms.common.entity.Catalog;
import com.cms.common.entity.Community;
import com.cms.common.entity.Worksheet;
import com.cms.common.util.MessageGenerator;
import com.cms.common.vo.community.CommunityDetailsVo;
import com.cms.common.vo.community.CommunityMemberInfoVo;
import com.cms.common.vo.community.CommunitySquareVo;
import com.cms.common.vo.community.MyCommunityVo;
import com.cms.common.vo.news.NewsWindowsVo;
import com.cms.web.feign.CommunityClient;
import com.cms.web.feign.MessageClient;
import com.cms.web.feign.WorksheetClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Creams
 */
@RestController
@RequestMapping("/community")
@Slf4j
public class CommunityController {

    @Autowired
    private CommunityClient communityClient;

    @Autowired
    private WorksheetClient worksheetClient;

    @Autowired
    private MessageClient messageClient;


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
    public ServerResponse<Worksheet> applicationCommunity(@RequestParam("name") String name,
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

        JSONObject object = new JSONObject();
        object.put("communityCatalog", catalogId);
        object.put("description", description);

        ServerResponse<Worksheet> response = worksheetClient.createWorksheet(name, id, content.toString(), 1, object.toJSONString());
        if (response.getCode() != 0) {
            return response;
        }

        List<Long> managerList = communityClient.manager(1).getData();
        messageClient.sendAll(managerList, MessageGenerator.VERIFY_TITLE, MessageGenerator.verifyContent("大连海事大学社团联合协会"));
        return ServerResponse.createSuccessResponse();
    }

    @GetMapping("/memberShip")
    public ServerResponse<Integer> memberShip(@RequestParam("userId") Long userId, @RequestParam("communityId") Integer communityId){
        return communityClient.memberShip(userId, communityId);
    }

    @PutMapping("/communityDescription")
    public ServerResponse communityDescription(@RequestParam("communityId") Integer communityId, @RequestParam("description") String description) {
        return communityClient.communityDescription(communityId, description);
    }

    @GetMapping("/newsWindow")
    public ServerResponse<List<NewsWindowsVo>> newWindow(@RequestParam("visible") Integer visible, @RequestParam("communityId") Integer communityId, Long userId) {
        return communityClient.newsWindow(visible, communityId, userId);
    }

    @PostMapping("news")
    public ServerResponse newsSubmit(@RequestParam("communityId") Integer communityId,
                              @RequestParam("userId") Long userId,
                              @RequestParam("visible") Byte visible,
                              @RequestParam("title") String title,
                              @RequestParam("content") String content) {
        return communityClient.newsSubmit(communityId, userId, visible, title, content);
    }

    @PostMapping("communityParticipation")
    public ServerResponse communityParticipation(@RequestParam("communityId") Integer communityId,
                                                 @RequestParam("userId") Long id, //user id
                                                 @RequestParam("content") String content) {

        ServerResponse<Integer> role = communityClient.memberShip(id, communityId);
        if (role.getCode() == 0) {
            return ServerResponse.createFailureResponse("您已是社团成员，请核对后重试");
        }

        Integer record = worksheetClient.applyRecord(communityId, id).getData();

        if (record > 0) {
            return ServerResponse.createFailureResponse("发现您有在流程中的申请记录，请耐心等待审核");
        }

        List<Community> communities = communityClient.communityId(communityId).getData();

        if (communities.size() == 0) {
            return ServerResponse.createFailureResponse("[BUG] Community not found");
        }

        ServerResponse serverResponse = worksheetClient.participation(communities.get(0).getName(), id, content);
        if (serverResponse.getCode() != 0) {
            return serverResponse;
        }

        List<Long> managerList = communityClient.manager(communityId).getData();

        messageClient.sendAll(managerList, MessageGenerator.VERIFY_TITLE, MessageGenerator.verifyContent(communities.get(0).getName()));
        return ServerResponse.createSuccessResponse();
    }

    @GetMapping("communityMember")
    public ServerResponse<List<CommunityMemberInfoVo>> communityMember(@RequestParam("communityId") Integer communityId) {
        return communityClient.communityMember(communityId);
    }

    @PutMapping("/roleChange")
    public ServerResponse roleChange(@RequestParam("executeId") Long executeId,
                                     @RequestParam("userNum") Long userNum,
                                     @RequestParam("communityId") Integer communityId,
                                     @RequestParam("roleId") Integer roleId) {

        return communityClient.roleChange(executeId, userNum, communityId, roleId);
    }

    @PutMapping("quit")
    public ServerResponse quit(@RequestParam("communityId") Integer communityId, @RequestParam("userId") Long userId) {
        return communityClient.quit(communityId, userId);
    }

    @PutMapping("/news/")
    public ServerResponse delete(@RequestParam("newsId") Integer newsId, @RequestParam("userId") Long userId) {
        return communityClient.deleteNews(newsId, userId);
    }

    @PutMapping("community")
    public ServerResponse logoutCommunity(@RequestParam("userId") Long userId, @RequestParam("communityId") Integer communityId) {
        return communityClient.logoutCommunity(communityId, userId);
    }
}
