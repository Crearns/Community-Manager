package com.cms.auth.service;

import com.cms.auth.dao.UserDao;
import com.cms.auth.feign.UserClient;
import com.cms.common.entity.User;
import com.cms.common.query.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Creams
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserQuery userQuery = new UserQuery();
        userQuery.setUserId(s);
        List<User> users = userDao.query(userQuery);
        if (users == null || users.size() == 0) {
            throw new UsernameNotFoundException("账号或密码不能为空");
        }
        return users.get(0);
    }
}
