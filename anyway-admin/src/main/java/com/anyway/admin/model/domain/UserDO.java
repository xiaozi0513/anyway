package com.anyway.admin.model.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表对象
 *
 * @author: wang_hui
 * @date: 2018/10/9 下午8:17
 */
@Data
public class UserDO implements Serializable {
    private static final long serialVersionUID = -2236461538591931963L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private int gender;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 排序值
     */
    private long order;

    /**
     * 状态
     */
    private int status;

    /**
     * 创建时间
     */
    private Date createdAt;
}
