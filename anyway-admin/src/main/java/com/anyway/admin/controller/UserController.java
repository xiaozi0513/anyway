package com.anyway.admin.controller;

import com.anyway.admin.core.annotation.LogEvent;
import com.anyway.admin.model.dto.UserDTO;
import com.anyway.admin.service.UserService;
import com.anyway.admin.common.util.Query;
import com.anyway.admin.common.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户管理
 *
 * @author: wang_hui
 * @date: 2018/10/10 上午10:05
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @LogEvent(result = false)
    @RequestMapping(value = "/list")
    public R listByPage(@RequestParam Map<String, Object> params) {
        List<UserDTO> userDTOs = userService.selectPage(new Query(params));
        return R.ok().put("list", userDTOs);
    }

    @LogEvent
    @RequestMapping(value = "/info/{id}")
    public R getById(@PathVariable("id") Long id) {
        UserDTO userDTO = userService.selectById(id);
        return R.ok().put("user", userDTO);
    }

    @LogEvent
    @RequestMapping(value = "/save")
    public R save(@RequestBody UserDTO user) {
        // TODO: 2018/10/10 校验

        userService.save(user);
        return R.ok();
    }

}
