package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Algorithmalarm;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(AlgorithmalarmDao)报警记录 表数据库访问层
//generate by redcloud,2020-07-24 19:59:20
public interface AlgorithmalarmDao {
    //@param id 主键 
    //@return 实例对象Algorithmalarm 
    Algorithmalarm queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> algorithmalarmList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countAlgorithmalarmList(@Param("jsonObj") JSONObject jsonObj);
// xxxxxxxxxxxxxxx 
// 没有处理的BtnMap:报警记录确认,/confirmState,confirmState 
// xxxxxxxxxxxxxxx 
// xxxxxxxxxxxxxxx 
// 没有处理的BtnMap:报警记录误报,/misstate,misstate 
// xxxxxxxxxxxxxxx 
    //@param jsonObj 过滤条件等 
    //@return 实例对象Algorithmalarm 
    int addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Algorithmalarm 
    int updateInst(@Param("jsonObj") JSONObject jsonObj);
}
