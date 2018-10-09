package com.anyway.common.util;

import java.util.HashMap;

/**
 * 返回数据模型
 *
 * @author: wang_hui
 * @date: 2018/10/9 下午5:58
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = -5323060105516606885L;

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
