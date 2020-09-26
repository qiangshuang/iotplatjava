package com.ipincloud.iotbj.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.api.utils.hik.ApiService;

import java.util.concurrent.Callable;

public class TheadUtils implements Callable {

    int i = 0;
    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"  i的值："+ i);
        JSONObject personNo = new JSONObject();
        personNo.put("personId", "5de8ebfb31274d1396c8d6e91c0a9596");
        JSONObject person = ApiService.getPersonbyPersonNo(personNo);
        i++;
        return JSON.toJSONString(person); //call方法可以有返回值
    }
}
