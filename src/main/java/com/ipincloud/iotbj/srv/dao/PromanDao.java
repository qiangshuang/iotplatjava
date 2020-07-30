package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Proman;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(PromanDao)产品管理 表数据库访问层
//generate by redcloud,2020-07-24 19:59:20
public interface PromanDao {
    //@param id 主键 
    //@return 实例对象Proman 
    Proman queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Proman 
    int addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Proman 
    int updateInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> promanList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countPromanList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 影响记录数 
    Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);
}
