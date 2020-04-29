package com.cms.message.service.impl;

import com.cms.common.entity.Message;
import com.cms.message.dao.MessageDao;
import com.cms.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Creams
 */

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    @Override
    public List<Message> findMessageByRecId(Long id) {
        return messageDao.findByReceiverId(id);
    }

    @Override
    public void readMark(Long id) {

    }

    @Override
    public void saveMessage(Message message) {

    }
}
