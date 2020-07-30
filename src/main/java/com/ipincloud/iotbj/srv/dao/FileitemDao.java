package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Fileitem;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(FileitemDao) 表数据库访问层
//generate by redcloud,2020-07-24 19:59:20
public interface FileitemDao {
    //@param id 主键 
    //@return 实例对象Fileitem 
    Fileitem queryById(Long id);
    //已处理，参看统一接口/hyupload
    //@param jsonObj 过滤条件等 
    //@return 实例对象Fileitem 
    int addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Fileitem 
    int updateInst(@Param("jsonObj") JSONObject jsonObj);
}
