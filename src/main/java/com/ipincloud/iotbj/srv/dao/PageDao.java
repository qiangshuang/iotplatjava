package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Page;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(PageDao) 表数据库访问层
//generate by redcloud,2020-07-24 19:59:20
public interface PageDao {
    //@param id 主键 
    //@return 实例对象Page 
    Page queryById(Long id);
    //@param userId 用户ID 
    //@return PageMap列表 
    List<Map> queryAllByUser(long userId);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Page 
    List<Map> pageRolePageMmlist(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数 
    Integer countPageRolePageMmlist(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Page 
    int addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Page 
    int updateInst(@Param("jsonObj") JSONObject jsonObj);
}
