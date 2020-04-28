package com.cms.community.dao;

import com.cms.common.entity.Community;
import com.cms.common.query.CommunityQuery;
import com.cms.common.vo.community.CommunityDetailsVo;
import com.cms.common.vo.community.CommunityMemberInfoVo;
import com.cms.common.vo.community.CommunitySquareVo;
import com.cms.common.vo.community.MyCommunityVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Community record);

    int insertSelective(Community record);

    Community selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Community record);

    int updateByPrimaryKey(Community record);

    List<CommunitySquareVo> getSquareList();

    int count();

    CommunityDetailsVo detailsInfo(Integer id);

    List<MyCommunityVo> getCommunityByUserId(String userId);

    List<Community> query(CommunityQuery communityQuery);

    Integer memberShip(@Param(("userId")) Long userId, @Param("communityId") Integer communityId);

    int member(@Param(("userId")) Long userId, @Param("communityId") Integer communityId, @Param("roleId") Integer roleId);

    int addHistoryNum(Integer communityId);

    List<CommunityMemberInfoVo> communityMember(Integer communityId);

    int roleChange(@Param("communityId") Integer communityId, @Param("userId") Long userId, @Param("roleId") Integer roleId);

    int memberDelete(@Param("communityId") Integer communityId, @Param("userId") Long userId);

    Long candidate(@Param("communityId") Integer communityId, @Param("userId") Long userId);
}