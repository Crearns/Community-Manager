package com.cms.community.service.impl;

import com.cms.common.entity.Community;
import com.cms.common.query.CommunityQuery;
import com.cms.common.util.PageBean;
import com.cms.common.vo.community.CommunityDetailsVo;
import com.cms.common.vo.community.CommunitySquareVo;
import com.cms.common.vo.community.MyCommunityVo;
import com.cms.community.dao.CommunityDao;
import com.cms.community.service.CommunityService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Creams
 */
@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityDao communityDao;

    @Override
    public List<CommunitySquareVo> getSquareList(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);

        List<CommunitySquareVo> squareList = communityDao.getSquareList();
        int count = squareList.size();
        PageBean<CommunitySquareVo> pageData = new PageBean<>(currentPage, pageSize, count);
        pageData.setItems(squareList);
        return pageData.getItems();
    }

    @Override
    public CommunityDetailsVo searchDetailsInfo(int communityId) {
        return communityDao.detailsInfo(communityId);
    }

    @Override
    public List<MyCommunityVo> getCommunityByUserId(String userId) {
        return communityDao.getCommunityByUserId(userId);
    }

    @Override
    public List<Community> query(CommunityQuery communityQuery) {
        return communityDao.query(communityQuery);
    }

    @Override
    public Integer memberShip(Long userId, Integer communityId) {
        return communityDao.memberShip(userId, communityId);
    }

    @Override
    public void updateDescription(Integer communityId, String description) {
        Community community = new Community();
        community.setId(communityId);
        community.setDescription(description);
        communityDao.updateByPrimaryKeySelective(community);
    }

    @Override
    public int member(Long userId, Integer communityId, Integer roleId) {
        return communityDao.member(userId, communityId, roleId);
    }

    @Override
    public int createCommunity(String name, Byte catalogId, String description) {
        Community community = new Community();
        community.setCatalogId(catalogId);
        community.setDescription(description);
        community.setName(name);
        return communityDao.insertSelective(community);
    }
}
