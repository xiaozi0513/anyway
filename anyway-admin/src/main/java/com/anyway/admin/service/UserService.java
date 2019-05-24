package com.anyway.admin.service;

import com.anyway.admin.model.dto.UserDTO;
import com.anyway.admin.common.util.Query;

import java.util.List;

/**
 * @author: wang_hui
 * @date: 2018/10/10 上午9:58
 */
public interface UserService {
    /**
     * 用户分页查询
     *
     * @param params 查询条件
     * @return
     */
    List<UserDTO> selectPage(Query params);

    /**
     * 查询单个用户信息（主键）
     *
     * @param id 主键
     * @return
     */
    UserDTO selectById(Long id);

    /**
     * 查询单个用户信息（用户名）
     *
     * @param username 用户名
     * @return
     */
    UserDTO selectByUsername(String username);

    /**
     * 添加用户
     *
     * @param user 用户信息
     */
    void save(UserDTO user);

}
