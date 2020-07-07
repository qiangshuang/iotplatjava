package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.UserCard;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)用户卡片 服务接口
//generate by redcloud,2020-07-07 23:53:41
public interface UserCardService {
    //@param id 主键 
    //@return 实例对象UserCard 
    UserCard queryById(Long id);
//@param jsonObj 调用参数 
//@return 实例对象UserCard 
JSONObject addInst( JSONObject jsonObj);
//@param jsonObj 过滤条件查询 
//@return 实例对象UserCard 
Map userCardList(JSONObject jsonObj);
}
