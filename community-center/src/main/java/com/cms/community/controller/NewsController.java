package com.cms.community.controller;

import com.cms.common.common.ResponseCode;
import com.cms.common.common.ServerResponse;
import com.cms.common.vo.news.NewsWindowsVo;
import com.cms.community.service.CommunityService;
import com.cms.community.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Creams
 */

@RestController
@RequestMapping("news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private CommunityService communityService;


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

        newsService.addNews(communityId, userId, visible, title, content);

        return ServerResponse.createSuccessResponse();
    }
}
