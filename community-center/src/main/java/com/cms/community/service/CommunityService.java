package com.cms.community.service;

import com.cms.common.entity.Community;
import com.cms.common.query.CommunityQuery;
import com.cms.common.vo.community.CommunityDetailsVo;
import com.cms.common.vo.community.CommunityMemberInfoVo;
import com.cms.common.vo.community.CommunitySquareVo;
import com.cms.common.vo.community.MyCommunityVo;

import java.util.List;

/**
 * @author Creams
 */
public interface CommunityService {
    List<CommunitySquareVo> getSquareList(int currentPage, int pageSize);

    CommunityDetailsVo searchDetailsInfo(int communityId);

    List<MyCommunityVo> getCommunityByUserId(String userId);

    List<Community> query(CommunityQuery communityQuery);

    Integer memberShip(Long userId, Integer communityId);

    void updateDescription(Integer communityId, String description);

    int member(Long userId, Integer communityId, Integer roleId);

    int createCommunity(String name, Byte catalogId, String description);

    int addHistoryNum(Integer communityId);

    List<CommunityMemberInfoVo> memberInfo(Integer communityId);

    int roleChange(Integer communityId, Long userId, Integer roleId);

    int deleteMember(Integer communityId, Long userId);

    Long candidate(Integer communityId, Long userId);

}
