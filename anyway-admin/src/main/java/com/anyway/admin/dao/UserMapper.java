package com.anyway.admin.dao;

import com.anyway.admin.model.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: wang_hui
 * @date: 2018/10/10 上午10:04
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * 分页条件查询
     *
     * @param params 筛选条件
     * @return 结果集
     */
    List<UserDO> selectByPage(Map<String, Object> params);

    /**
     * 获取单个用户
     *
     * @param id 主键
     * @return 用户对象
     */
    UserDO selectById(Long id);

    /**
     * 获取单个用户
     *
     * @param username 用户名
     * @return
     */
    UserDO selectByUsername(String username);

    /**
     * 新增用户
     *
     * @param userDO 用户对象
     */
    void insert(UserDO userDO);
}
