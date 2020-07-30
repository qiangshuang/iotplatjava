package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Btn;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(BtnDao) 表数据库访问层
//generate by redcloud,2020-07-24 19:59:20
public interface BtnDao {
    //@param id 主键 
    //@return 实例对象Btn 
    Btn queryById(Long id);
    //@param userId 用户ID 
    //@return 实例Btn列表 
    List<Btn> queryByUserId(long userId);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> btnList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countBtnList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Btn 
    int addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Btn 
    int updateInst(@Param("jsonObj") JSONObject jsonObj);
}
