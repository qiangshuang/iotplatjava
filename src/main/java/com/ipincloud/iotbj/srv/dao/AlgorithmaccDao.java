package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Algorithmacc;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(AlgorithmaccDao)算法接入 表数据库访问层
//generate by redcloud,2020-07-07 23:53:41
public interface AlgorithmaccDao {
    //@param id 主键 
    //@return 实例对象Algorithmacc 
    Algorithmacc queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> algorithmaccList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countAlgorithmaccList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Algorithmacc 
    Long addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Algorithmacc 
    void updateInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 影响记录数 
    Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);
}
