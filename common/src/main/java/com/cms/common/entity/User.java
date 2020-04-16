package com.cms.common.entity;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author Creams
 */
@Data
public class User implements Serializable, UserDetails {
    private Long id;
    private Date gmtCreate;
    private Date gmtModified;
    private String userId;
    private String password;
    private String realName;
    private Long userNum;
    private Long phone;
    private String email;
    private String nickname;
    private Integer grade;
    private Integer classNum;
    private String college;
    private String major;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}