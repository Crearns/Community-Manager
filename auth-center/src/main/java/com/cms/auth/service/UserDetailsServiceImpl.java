package com.cms.auth.service;

import com.cms.auth.feign.UserClient;
import com.cms.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Creams
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userClient.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("username: " + s + " not found");
        }

        return user;
    }
}
