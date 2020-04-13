package com.cms.auth.service.impl;

import com.cms.auth.dao.UserDao;
import com.cms.auth.service.UserService;
import com.cms.common.entity.User;
import com.cms.common.query.UserQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Creams
 */

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> query(UserQuery userQuery) {
        return userDao.query(userQuery);
    }

    @Override
    public User selectByUserId(String userId) {
        UserQuery query = new UserQuery();
        query.setUserId(userId);
        List<User> users = userDao.query(query);
        return CollectionUtils.isEmpty(users) ? null : users.get(0);
    }


}
