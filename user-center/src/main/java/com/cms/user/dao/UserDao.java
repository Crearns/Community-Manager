package com.cms.user.dao;

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

    Integer updateUserSessionIdByUserId(@Param("id") Integer id, @Param("sessionId") String sessionId);

    Integer updateCommonInfo(User user);

    Integer updateSelectiveById(User user);
}
