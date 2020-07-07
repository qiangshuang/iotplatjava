package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Person;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(PersonDao) 表数据库访问层
//generate by redcloud,2020-07-07 23:53:41
public interface PersonDao {
    //@param id 主键 
    //@return 实例对象Person 
    Person queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> personList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countPersonList(@Param("jsonObj") JSONObject jsonObj);
}
