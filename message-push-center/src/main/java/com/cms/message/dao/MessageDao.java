package com.cms.message.dao;

import com.cms.common.entity.Message;

import java.util.List;

/**
 * @author Creams
 */
public interface MessageDao {

    void save(Message message);

//    void update(Message message);

    List<Message> findAll();

    void delete(Integer id);

    List<Message> findByReceiverId(Long id);
}
