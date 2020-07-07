package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Visitperson;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(VisitpersonDao) 表数据库访问层
//generate by redcloud,2020-07-08 01:57:14
public interface VisitpersonDao {
    //@param id 主键 
    //@return 实例对象Visitperson 
    Visitperson queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> visitpersonList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countVisitpersonList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Visitperson 
    Long addInst(@Param("jsonObj") JSONObject jsonObj);
}
