package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Job;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(JobDao) 表数据库访问层
//generate by redcloud,2020-07-24 19:59:20
public interface JobDao {
    //@param id 主键 
    //@return 实例对象Job 
    Job queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> jobList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countJobList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Job 
    int addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Job 
    int updateInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Job 
    Integer deleteInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 影响记录数 
    Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);
}
