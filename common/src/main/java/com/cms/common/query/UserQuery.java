package com.cms.common.query;

import lombok.Data;

import java.util.Date;

/**
 * @author Creams
 */

@Data
public class UserQuery {
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
}
