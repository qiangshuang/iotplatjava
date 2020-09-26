package com.ipincloud.iotbj.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.api.utils.hik.ApiModel;
import com.ipincloud.iotbj.api.utils.hik.ApiService;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Mytest implements Callable {
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

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Callable callable = new Mytest();
        for (int i = 0; i < 10; i++) {
            FutureTask task = new FutureTask(callable);
            new Thread(task,"子线程"+ i).start();
            try {
                //获取子线程的返回值
                System.out.println("子线程返回值："+task.get() + "\n");
            }  catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println(Thread.currentThread().getName()+"  i的值："+ i);
        }
        long end = System.currentTimeMillis();
        System.out.println("执行时间： "+ (end-start));
    }

    //2266
    //2614
}
