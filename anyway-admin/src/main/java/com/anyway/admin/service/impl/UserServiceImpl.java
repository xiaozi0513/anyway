package com.anyway.admin.service.impl;

import com.anyway.admin.dao.UserMapper;
import com.anyway.admin.model.convert.UserConvert;
import com.anyway.admin.model.domain.UserDO;
import com.anyway.admin.model.dto.UserDTO;
import com.anyway.admin.service.UserService;
import com.anyway.admin.common.constant.CodeEnum;
import com.anyway.admin.common.exception.AwException;
import com.anyway.admin.common.util.Query;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: wang_hui
 * @date: 2018/10/10 上午9:58
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDTO> selectPage(Query params) {
        PageInfo<UserDO> pageInfo = PageHelper.offsetPage(params.getOffset(), params.getLimit()).doSelectPageInfo(() -> userMapper.selectByPage(params));
        List<UserDTO> userDTOs = UserConvert.MAPPER.dos2dtos(pageInfo.getList());
        return userDTOs;
    }

    @Override
    public UserDTO selectById(Long id) {
        return UserConvert.MAPPER.do2dto(userMapper.selectById(id));
    }

    @Override
    public UserDTO selectByUsername(String username) {
        return UserConvert.MAPPER.do2dto(userMapper.selectByUsername(username));
    }

    @Override
    public void save(UserDTO user) {
        if (userMapper.selectByUsername(user.getUsername()) != null) {
            throw new AwException(CodeEnum.USER_EXIST.getCode(), CodeEnum.USER_EXIST.getMessage());
        }
        UserDO userDO = UserConvert.MAPPER.dto2do(user);
        userDO.setPassword("11111111");// TODO: 2018/10/10 加密
        userMapper.insert(userDO);
    }
}
