package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.User;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj) 服务接口
//generate by redcloud,2020-07-08 01:57:14
public interface UserService {
    //@param id 主键 
    //@return 实例对象User 
    User queryById(Long id);
    //@param userName 账号 
    //@return 实例对象User 
    User queryByUsername(String userName);
    //已处理，查看服务:/useraccount 
//@param jsonObj 调用参数 
//@return 实例对象User 
JSONObject addInst( JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 影响记录数User 
void updateInst(JSONObject jsonObj);
//@param jsonObj 过滤条件查询 
//@return 实例对象User 
Map userList(JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象User 
    Map userUserRoleListJoin(JSONObject jsonObj);
}
