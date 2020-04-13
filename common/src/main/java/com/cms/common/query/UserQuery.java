package com.cms.common.query;

import lombok.Data;

import java.util.Date;

/**
 * @author Creams
 */

@Data
public class UserQuery {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 账号
     */
    private String userId;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 学号
     */
    private Integer userNum;

    /**
     * 手机
     */
    private Long phone;

    /**
     * 邮箱
     */
    private String email;

}
