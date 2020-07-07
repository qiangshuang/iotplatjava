package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Access;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(AccessDao)门禁编辑 表数据库访问层
//generate by redcloud,2020-07-08 01:57:14
public interface AccessDao {
    //@param id 主键 
    //@return 实例对象Access 
    Access queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Access 
    Map accessGetAttr(@Param("jsonObj") JSONObject jsonObj);
}
