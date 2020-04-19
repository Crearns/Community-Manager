package com.cms.community.service;

import com.cms.common.vo.community.CommunityDetailsVo;
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
}
