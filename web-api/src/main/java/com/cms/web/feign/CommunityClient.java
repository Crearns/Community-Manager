package com.cms.web.feign;

import com.cms.common.common.ServerResponse;
import com.cms.common.entity.Catalog;
import com.cms.common.vo.community.CommunityDetailsVo;
import com.cms.common.vo.community.CommunitySquareVo;
import com.cms.common.vo.community.MyCommunityVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

}
