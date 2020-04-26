package com.cms.community.dao;

import com.cms.common.entity.News;
import com.cms.common.vo.news.NewsWindowsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NewsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    List<NewsWindowsVo> selectAllNews(@Param("visible") Integer visible, @Param("communityId") Integer communityId);
}