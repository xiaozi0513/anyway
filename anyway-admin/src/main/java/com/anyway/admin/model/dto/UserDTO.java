package com.anyway.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: wang_hui
 * @date: 2018/10/10 下午3:21
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 4383837916118184613L;

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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
}
