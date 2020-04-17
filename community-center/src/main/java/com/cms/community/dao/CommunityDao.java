package com.cms.community.dao;

import com.cms.common.entity.Community;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Community record);

    int insertSelective(Community record);

    Community selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Community record);

    int updateByPrimaryKey(Community record);
}