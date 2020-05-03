package com.cms.message.dao;

import com.cms.common.entity.Message;

import java.util.List;

/**
 * @author Creams
 */
public interface MessageDao {

    void save(Message message);

    List<Message> findAll();

    void delete(String id);

    List<Message> findByReceiverId(Long id);

    void markRead(String id);

    int unReadCount(Long id);

    void readAll(Long userId);
}
