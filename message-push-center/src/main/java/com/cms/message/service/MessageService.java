package com.cms.message.service;

import com.cms.common.entity.Message;

import java.util.List;

/**
 * @author Creams
 */
public interface MessageService {

    List<Message> findMessageByRecId(Long id);

    void readMark(String id);

    void saveMessage(Message message);

    Integer getUnReadCount(Long id);

    void readAll(Long id);

}
