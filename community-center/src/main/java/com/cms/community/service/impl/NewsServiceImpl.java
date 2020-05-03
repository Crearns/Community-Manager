package com.cms.community.service.impl;

import com.cms.common.entity.News;
import com.cms.common.vo.news.NewsWindowsVo;
import com.cms.community.dao.NewsDao;
import com.cms.community.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Creams
 */

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Override
    public List<NewsWindowsVo> getWindow(Integer visible, Integer communityId) {
        return newsDao.selectAllNews(visible, communityId);
    }

    @Override
    public void addNews(Integer communityId, Long userId, Byte visible, String title, String content) {
        News news = News.builder()
                .title(title)
                .content(content)
                .authorId(userId)
                .communityId(communityId)
                .visible(visible)
                .build();

        newsDao.insertSelective(news);
    }

    @Override
    public int delete(Integer id) {
        return newsDao.deleteByPrimaryKey(id);
    }

    @Override
    public News getNewsById(Integer newsId) {
        return newsDao.selectByPrimaryKey(newsId);
    }
}
