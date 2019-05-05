package com.anyway.client.remote.fallback;

import com.anyway.client.remote.feign.AnywayAdminRemote;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: wang_hui
 * @date: 2019/4/28 上午11:07
 */
@Component
public class AnywayAdminRemoteFallback implements AnywayAdminRemote {
    @Override
    public String listUser() {
        return "AnywayAdminRemote#listUser() fallback";
    }

    @Override
    public String getUserById(@PathVariable("id") Long userId) {
        return null;
    }
}
