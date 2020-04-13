package com.cms.user.service;


import com.cms.common.entity.User;
import com.cms.common.query.UserQuery;

import java.util.List;

/**
 * @author Creams
 */
public interface UserService{
    List<User> query(UserQuery userQuery);

    User selectByUserId(String userId);
}
