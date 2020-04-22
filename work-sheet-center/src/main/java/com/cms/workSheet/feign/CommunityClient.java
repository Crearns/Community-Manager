package com.cms.workSheet.feign;

import com.cms.common.common.ServerResponse;
import com.cms.common.entity.Community;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Creams
 */

@FeignClient("community-service")
public interface CommunityClient {

    @GetMapping("community/community")
    ServerResponse<List<Community>> community(@RequestParam("name") String name);
}
