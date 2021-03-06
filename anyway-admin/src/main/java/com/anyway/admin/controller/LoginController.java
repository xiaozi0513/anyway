package com.anyway.admin.controller;

import com.anyway.admin.core.annotation.LogEvent;
import com.anyway.admin.model.dto.UserDTO;
import com.anyway.admin.service.UserService;
import com.anyway.admin.common.constant.CodeEnum;
import com.anyway.admin.common.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wang_hui
 * @date: 2018/10/10 下午5:51
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @LogEvent
    @RequestMapping(value = "/login")
    public R login(String username, String password) {
        UserDTO userDTO = userService.selectByUsername(username);
        if (userDTO == null) {
            return R.error(CodeEnum.USER_NO_EXIST.getCode(), CodeEnum.USER_NO_EXIST.getMessage());
        }
        if (!userDTO.getPassword().equals(password)) {
            return R.error(CodeEnum.PWD_ERROR.getCode(), CodeEnum.PWD_ERROR.getMessage());
        }
        return R.ok();
    }

}
