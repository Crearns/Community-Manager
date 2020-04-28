package com.cms.web.feign;

import com.cms.common.common.ServerResponse;
import com.cms.common.entity.Catalog;
import com.cms.common.entity.Community;
import com.cms.common.vo.community.CommunityDetailsVo;
import com.cms.common.vo.community.CommunityMemberInfoVo;
import com.cms.common.vo.community.CommunitySquareVo;
import com.cms.common.vo.community.MyCommunityVo;
import com.cms.common.vo.news.NewsWindowsVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @author Creams
 */

@FeignClient("community-service")
public interface CommunityClient {

    @GetMapping(value = "/community/squareList")
    ServerResponse<List<CommunitySquareVo>> squareList(int currentPage);

    @GetMapping("/community/communityDetails")
    ServerResponse<CommunityDetailsVo> details(@RequestParam("communityId") int communityId);

    @GetMapping("/community/userCommunity")
    ServerResponse<List<MyCommunityVo>> myCommunity(@RequestParam("userId") String userId);

    @GetMapping("/community/catalogList")
    ServerResponse<List<Catalog>> catalogList();

    @GetMapping("/community/catalog")
    ServerResponse<Catalog> catalog(@RequestParam("id") Byte id);

    @GetMapping("/community/memberShip")
    ServerResponse<Integer> memberShip(@RequestParam("userId") Long userId, @RequestParam("communityId") Integer communityId);

    @PutMapping("/community/communityDescription")
    ServerResponse communityDescription(@RequestParam("communityId") Integer communityId, @RequestParam("description") String description);

    @GetMapping("/community/news/newsWindow")
    ServerResponse<List<NewsWindowsVo>> newsWindow(@RequestParam("visible") Integer visible, @RequestParam("communityId") Integer communityId, @RequestParam("userId") Long userId);

    @PostMapping("community/news/news")
    ServerResponse newsSubmit(@RequestParam("communityId") Integer communityId,
                              @RequestParam("userId") Long userId,
                              @RequestParam("visible") Byte visible,
                              @RequestParam("title") String title,
                              @RequestParam("content") String content);


    @GetMapping("/community/community-id")
    ServerResponse<List<Community>> communityId(@RequestParam("communityId") Integer communityId);

    @PostMapping("/community/member")
    ServerResponse member(@RequestParam("communityId") Integer communityId, @RequestParam("userId") Long userId, @RequestParam("roleId") Integer roleId);

    @GetMapping("/community/community")
    ServerResponse<List<Community>> community(@RequestParam("name") String name);

    @PostMapping("/community/community")
    ServerResponse<Integer> community(@RequestParam("name") String name,
                                    @RequestParam("catalog") Byte catalog,
                                    @RequestParam("description") String description);

    @PutMapping("/community/historyNum")
    ServerResponse historyNum(@RequestParam("communityId") Integer communityId);


    @GetMapping("/community/communityMember")
    ServerResponse<List<CommunityMemberInfoVo>> communityMember(@RequestParam("communityId") Integer communityId);


    @PutMapping("/community/roleChange")
    ServerResponse roleChange(       @RequestParam("executeId") Long executeId,
                                     @RequestParam("userNum") Long userNum,
                                     @RequestParam("communityId") Integer communityId,
                                     @RequestParam("roleId") Integer roleId);

    @DeleteMapping("/community/quit")
    ServerResponse quit(@RequestParam("communityId") Integer communityId, @RequestParam("userId") Long userId);

}
