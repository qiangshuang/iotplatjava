package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.UserData;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(UserDataDao) 表数据库访问层
//generate by redcloud,2020-07-07 10:18:16
public interface UserDataDao {
    //@param id 主键 
    //@return 实例对象UserData 
    UserData queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象UserData 
    List<Map> userDataQuery(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return JSONObject 
    void userDataSetRelation(@Param("jsonObj") JSONObject jsonObj);
}
