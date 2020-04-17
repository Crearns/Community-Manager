package com.cms.gateway.feign;

import com.cms.common.vo.community.CommunitySquareVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Creams
 */

@FeignClient("community-service")
public interface CommunityClient {

    @GetMapping("community/squareList")
    List<CommunitySquareVo> squareList(int currentPage);
}
