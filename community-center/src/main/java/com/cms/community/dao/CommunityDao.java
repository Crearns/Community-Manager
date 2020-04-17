package com.cms.community.dao;

import com.cms.common.entity.Community;
import com.cms.common.vo.community.CommunitySquareVo;
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
}