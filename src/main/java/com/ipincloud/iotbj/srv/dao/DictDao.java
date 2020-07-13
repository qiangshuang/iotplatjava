package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Dict;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(DictDao) 表数据库访问层
//generate by redcloud,2020-07-07 10:18:15
public interface DictDao {
    //@param id 主键 
    //@return 实例对象Dict 
    Dict queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> dictList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countDictList(@Param("jsonObj") JSONObject jsonObj);
}
