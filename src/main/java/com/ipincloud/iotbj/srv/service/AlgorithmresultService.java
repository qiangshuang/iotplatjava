package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Algorithmresult;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)算法结果 服务接口
//generate by redcloud,2020-07-24 19:59:20
public interface AlgorithmresultService {
    //@param id 主键 
    //@return 实例对象Algorithmresult 
    Algorithmresult queryById(Long id);
//@param jsonObj 调用参数 
//@return 实例对象Algorithmresult 
JSONObject addInst( JSONObject jsonObj);
}
