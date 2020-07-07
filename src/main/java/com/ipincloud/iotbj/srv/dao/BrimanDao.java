package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Briman;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(BrimanDao)网桥管理 表数据库访问层
//generate by redcloud,2020-07-08 01:57:14
public interface BrimanDao {
    //@param id 主键 
    //@return 实例对象Briman 
    Briman queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Briman 
    void updateInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> brimanList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countBrimanList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Briman 
    Long addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 影响记录数 
    Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);
}
