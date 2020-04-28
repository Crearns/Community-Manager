package com.cms.community.controller;

import com.cms.common.common.ResponseCode;
import com.cms.common.common.ServerResponse;
import com.cms.common.entity.Community;
import com.cms.common.entity.User;
import com.cms.common.query.CommunityQuery;
import com.cms.common.vo.community.CommunityDetailsVo;
import com.cms.common.vo.community.CommunityMemberInfoVo;
import com.cms.common.vo.community.CommunitySquareVo;
import com.cms.common.vo.community.MyCommunityVo;
import com.cms.community.feign.UserClient;
import com.cms.community.service.CommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Queue;

/**
 * @author Creams
 */

@RestController
@Slf4j
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private UserClient userClient;

    @GetMapping("/squareList")
    public ServerResponse<List<CommunitySquareVo>> squareList(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {
        return ServerResponse.createSuccessResponse(communityService.getSquareList(currentPage, 20));
    }

    @GetMapping("/communityDetails")
    public ServerResponse<CommunityDetailsVo> details(@RequestParam("communityId") int communityId) {
        CommunityDetailsVo details = communityService.searchDetailsInfo(communityId);
        if (details != null) {
            return ServerResponse.createSuccessResponse(details);
        }
        return ServerResponse.createFailureResponse(ResponseCode.NULL);
    }

    @GetMapping("/userCommunity")
    public ServerResponse<List<MyCommunityVo>> userCommunity(String userId) {
        List<MyCommunityVo> res = communityService.getCommunityByUserId(userId);

        if (CollectionUtils.isEmpty(res)) {
            return ServerResponse.createFailureResponse(ResponseCode.NULL);
        }

        return ServerResponse.createSuccessResponse(res);
    }


    @GetMapping("/community")
    public ServerResponse<List<Community>> community(@RequestParam("name") String name) {
        CommunityQuery query = new CommunityQuery();
        query.setName(name);
        return ServerResponse.createSuccessResponse(communityService.query(query));
    }

    @GetMapping("/community-id")
    public ServerResponse<List<Community>> community(@RequestParam("communityId") Integer communityId) {
        CommunityQuery query = new CommunityQuery();
        query.setId(communityId);
        return ServerResponse.createSuccessResponse(communityService.query(query));
    }


    @GetMapping("/memberShip")
    public ServerResponse<Integer> memberShip(@RequestParam("userId") Long userId, @RequestParam("communityId") Integer communityId) {
        Integer role = communityService.memberShip(userId, communityId);
        if (role == null) {
            return ServerResponse.createFailureResponse(ResponseCode.NO_PERMISSION);
        }

        return ServerResponse.createSuccessResponse(role);
    }

    @PutMapping("/communityDescription")
    public ServerResponse communityDescription(@RequestParam("communityId") Integer communityId, @RequestParam("description") String description) {
        communityService.updateDescription(communityId, description);
        return ServerResponse.createSuccessResponse();
    }

    @PostMapping("/member")
    public ServerResponse member(@RequestParam("communityId") Integer communityId, @RequestParam("userId") Long userId, @RequestParam("roleId") Integer roleId) {
        return ServerResponse.createSuccessResponse(communityService.member(userId, communityId, roleId));
    }

    @PostMapping("/community")
    public ServerResponse<Integer> community(@RequestParam("name") String name,
                                    @RequestParam("catalog") Byte catalog,
                                    @RequestParam("description") String description) {
        CommunityQuery communityQuery = new CommunityQuery();
        communityQuery.setName(name);
        List<Community> communities = communityService.query(communityQuery);
        if (!communities.isEmpty()) {
            return ServerResponse.createFailureResponse(name + "社团已经存在");
        }

        return ServerResponse.createSuccessResponse(communityService.createCommunity(name, catalog, description));
    }

    @PutMapping("/historyNum")
    public ServerResponse historyNum(@RequestParam("communityId") Integer communityId) {
        return ServerResponse.createSuccessResponse(communityService.addHistoryNum(communityId));
    }

    @GetMapping("/communityMember")
    public ServerResponse<List<CommunityMemberInfoVo>> communityMember(@RequestParam("communityId") Integer communityId) {
        return ServerResponse.createSuccessResponse(communityService.memberInfo(communityId));
    }


    @PutMapping("/roleChange")
    public ServerResponse roleChange(@RequestParam("executeId") Long executeId,
                                     @RequestParam("userNum") Long userNum,
                                     @RequestParam("communityId") Integer communityId,
                                     @RequestParam("roleId") Integer roleId) {

        User user = userClient.userNum(userNum).getData();

        if (user == null) {
            return ServerResponse.createFailureResponse("[BUG] 找不到此用户");
        }

        if (executeId.equals(user.getId())) {
            return ServerResponse.createFailureResponse("你不能对自己操作");
        }

        User executor = userClient.userId(executeId).getData();

        Integer executorRole = communityService.memberShip(executor.getId(), communityId);
        Integer role = communityService.memberShip(user.getId(), communityId);

        if (role == null) {
            log.error("[BUG] 找不到该成员在社团的记录, user id:{}", user.getId());
            return ServerResponse.createFailureResponse("找不到该成员在社团的记录");
        } else {
            if (role.equals(roleId)) {
                return ServerResponse.createFailureResponse("该成员已经是该职位");
            }
        }

        if (executorRole == null || executorRole == 3) {
            return ServerResponse.createFailureResponse("您无权进行此操作");
        }

        if (roleId == 4) {
            communityService.deleteMember(communityId, user.getId());
            return ServerResponse.createSuccessResponse();
        }

        if (roleId == 1) {
            if (executorRole != 1) {
                return ServerResponse.createFailureResponse("您无权进行此操作");
            }

            communityService.roleChange(communityId, user.getId(), roleId);
            communityService.roleChange(communityId, executeId, 2);

            return ServerResponse.createSuccessResponse();
        } else {
            return ServerResponse.createSuccessResponse(communityService.roleChange(communityId, user.getId(), roleId));
        }
    }

    @DeleteMapping("quit")
    public ServerResponse quit(@RequestParam("communityId") Integer communityId, @RequestParam("userId") Long userId) {
        Integer role = communityService.memberShip(userId, communityId);

        if (role == null) {
            return ServerResponse.createFailureResponse("您无权执行此操作");
        }

        CommunityQuery communityQuery = new CommunityQuery();
        communityQuery.setId(communityId);
        Community community = communityService.query(communityQuery).get(0);

        if (community == null) {
            log.error("[BUG] 出现未知错误, community id:{} 不存在", communityId);
            return ServerResponse.createFailureResponse("出现未知错误");
        }

        if (role == 1) {
            Long candidateId = communityService.candidate(communityId, userId);
            if (candidateId == null) {
                return ServerResponse.createFailureResponse("找不到团长候选人，若要退出社团，请直接注销");
            }

            communityService.roleChange(communityId, candidateId, 1);
            communityService.deleteMember(communityId, userId);
            return ServerResponse.createSuccessResponse("您已成功退出，后会有期");
        } else {
            communityService.deleteMember(communityId, userId);
            return ServerResponse.createSuccessResponse("您已成功退出，后会有期");
        }
    }
}
