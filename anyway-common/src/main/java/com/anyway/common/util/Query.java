package com.anyway.common.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询条件参数封装类
 *
 * @author: wang_hui
 * @date: 2018/10/11 上午10:10
 */
public class Query extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 6948827975663191510L;

    private int offset;
    private int page;
    private int limit = 10;

    public Query(Map<String, Object> params) {
        this.putAll(params);
        if (null != params.get("limit")) {
            limit = Integer.parseInt(params.get("limit").toString());
            if (null != params.get("offset")) {
                offset = Integer.parseInt(params.get("offset").toString());
            }
            if (null != params.get("page")) {
                page = Integer.parseInt(params.get("page").toString());
                offset = page > 0 ? (page - 1) * limit : 0;
            }
        }
        this.put("limit", limit);
        this.put("offset", offset);
        this.put("page", page);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
