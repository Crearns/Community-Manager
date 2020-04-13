package com.cms.auth.dao;


import com.cms.common.entity.User;
import com.cms.common.query.UserQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    List<User> query(UserQuery userQuery);
}