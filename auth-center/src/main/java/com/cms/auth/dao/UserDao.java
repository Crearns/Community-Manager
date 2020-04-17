package com.cms.auth.dao;

import com.cms.common.entity.User;
import com.cms.common.query.UserQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Creams
 */

@Repository
public interface UserDao {
    List<User> query(UserQuery userQuery);

    Integer updateUserSessionIdByUserId(@Param("userId") String id, @Param("sessionId") String sessionID);
}
