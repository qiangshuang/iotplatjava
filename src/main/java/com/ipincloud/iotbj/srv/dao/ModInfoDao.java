package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.ModInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(ModInfoDao)模型表 表数据库访问层
//generate by redcloud,2020-07-24 19:59:20
public interface ModInfoDao {
    //@param id 主键 
    //@return 实例对象ModInfo 
    ModInfo queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象ModInfo 
    int addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象ModInfo 
    int updateInst(@Param("jsonObj") JSONObject jsonObj);
}
