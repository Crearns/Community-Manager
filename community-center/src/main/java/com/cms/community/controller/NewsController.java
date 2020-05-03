package com.cms.community.controller;

import com.cms.common.common.ResponseCode;
import com.cms.common.common.ServerResponse;
import com.cms.common.entity.Community;
import com.cms.common.entity.News;
import com.cms.common.query.CommunityQuery;
import com.cms.common.util.MessageGenerator;
import com.cms.common.vo.news.NewsWindowsVo;
import com.cms.community.feign.MessageClient;
import com.cms.community.service.CommunityService;
import com.cms.community.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Creams
 */

@RestController
@RequestMapping("news")
@Slf4j
public class NewsController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private MessageClient messageClient;


    @GetMapping("/newsWindow")
    public ServerResponse<List<NewsWindowsVo>> newsWindow(@RequestParam("visible") Integer visible, @RequestParam("communityId") Integer communityId, Long userId) {
        if (visible == 0) {
            if (userId == null) {
                return ServerResponse.createFailureResponse(ResponseCode.NO_PERMISSION);
            }

            Integer role = communityService.memberShip(userId, communityId);

            if (role == null) {
                return ServerResponse.createFailureResponse(ResponseCode.NO_PERMISSION);
            }

            return ServerResponse.createSuccessResponse(newsService.getWindow(visible, communityId));
        } else {
            return ServerResponse.createSuccessResponse(newsService.getWindow(visible, communityId));
        }
    }

    @PostMapping("/news")
    public ServerResponse newsSubmit(@RequestParam("communityId") Integer communityId,
                                     @RequestParam("userId") Long userId,
                                     @RequestParam("visible") Byte visible,
                                     @RequestParam("title") String title,
                                     @RequestParam("content") String content) {

        if (userId == null) {
            return ServerResponse.createFailureResponse(ResponseCode.NO_PERMISSION);
        }

        Integer role = communityService.memberShip(userId, communityId);

        if (role == null) {
            return ServerResponse.createFailureResponse(ResponseCode.NO_PERMISSION);
        }

        CommunityQuery communityQuery = new CommunityQuery();
        communityQuery.setId(communityId);

        Community community = communityService.query(communityQuery).get(0);

        if (community == null) {
            return ServerResponse.createFailureResponse(ResponseCode.NO_PERMISSION);
        }

        newsService.addNews(communityId, userId, visible, title, content);

        List<Long> memberList = communityService.getAllMemberId(communityId);

        messageClient.sendAll(memberList, MessageGenerator.NEWS_TITLE, MessageGenerator.newsContent(community.getName()));

        return ServerResponse.createSuccessResponse();
    }

    @DeleteMapping("news")
    public ServerResponse delete(@RequestParam("newsId") Integer newsId, @RequestParam("userId") Long userId) {
        News news = newsService.getNewsById(newsId);

        if (news == null) {
            return ServerResponse.createFailureResponse(ResponseCode.NULL);
        }

        Integer role = communityService.memberShip(userId, news.getCommunityId());

        if (role == null || role == 3) {
            return ServerResponse.createFailureResponse(ResponseCode.NO_PERMISSION);
        }

        return ServerResponse.createSuccessResponse(newsService.delete(newsId));
    }
}
