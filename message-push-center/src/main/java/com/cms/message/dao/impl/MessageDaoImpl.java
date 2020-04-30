package com.cms.message.dao.impl;

import com.cms.common.entity.Message;
import com.cms.message.dao.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
        Query query = new Query(Criteria.where("receiverId").is(id));
        return mongoTemplate.find(query, Message.class);
    }

    @Override
    public void markRead(String id) {
        Query query = new Query(Criteria.where("_id").is(id));

        Update update = new Update();
        update.set("read", true);

        mongoTemplate.updateFirst(query, update, Message.class);
    }

    @Override
    public int unReadCount(Long id) {
        Query query = new Query(Criteria.where("receiverId").is(id).and("read").is(false));
        return mongoTemplate.find(query, Message.class).size();
    }

    @Override
    public void readAll(Long userId) {
        Query query = new Query(Criteria.where("receiverId").is(userId));

        Update update = new Update();
        update.set("read", true);

        mongoTemplate.updateMulti(query, update, Message.class);
    }
}
