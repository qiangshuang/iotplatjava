package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.ModCamera;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(ModCameraDao)摄像机 表数据库访问层
//generate by redcloud,2020-07-24 19:59:20
public interface ModCameraDao {
    //@param id 主键 
    //@return 实例对象ModCamera 
    ModCamera queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象ModCamera 
    int addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象ModCamera 
    int updateInst(@Param("jsonObj") JSONObject jsonObj);
}
