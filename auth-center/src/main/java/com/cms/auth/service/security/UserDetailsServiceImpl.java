package com.cms.auth.service.security;

import com.cms.auth.feign.UserClient;
import com.cms.auth.service.UserService;
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
    private UserService userService;

    @Autowired
    private UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userClient.findByUsername(s);
////        System.out.println(user);
//        User user = userService.selectByUserId(s);
        if (user == null) {
            throw new UsernameNotFoundException("username: " + s + " not found");
        }

        org.springframework.security.core.userdetails.User securityUser
                = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("client"));
        return securityUser;
    }
}
