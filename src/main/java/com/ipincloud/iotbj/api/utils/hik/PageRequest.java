package com.ipincloud.iotbj.api.utils.hik;

import java.util.HashMap;

public class PageRequest extends HashMap {
    public int pageNo;
    public int pageSize;

    public PageRequest(int pageNo, int pageSize) {
        if (pageNo <= 0 || pageSize <= 0 || pageSize > 1000)
            throw new IllegalArgumentException();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
