package com.cms.message.dao.impl;

import com.cms.common.entity.Message;
import com.cms.message.dao.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Creams
 */

@Repository
public class MessageDaoImpl implements MessageDao {
    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public void save(Message message) {
        mongoTemplate.save(message);
    }

    @Override
    public List<Message> findAll() {
        return mongoTemplate.findAll(Message.class);
    }

    @Override
    public void delete(Integer id) {
        Message message = mongoTemplate.findById(id, Message.class);
        if (message != null) {
            mongoTemplate.remove(message);
        }
    }

    @Override
    public List<Message> findByReceiverId(Long id) {
        Query query = new Query(Criteria.where("receiveId").is(id));
        return mongoTemplate.find(query, Message.class);
    }
}
