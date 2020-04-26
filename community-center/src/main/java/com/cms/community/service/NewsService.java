package com.cms.community.service;

import com.cms.common.vo.news.NewsWindowsVo;

import java.util.List;

/**
 * @author Creams
 */
public interface NewsService {
    List<NewsWindowsVo> getWindow(Integer visible, Integer communityId);

    void addNews(Integer communityId, Long userId, Byte visible, String title, String content);
}
