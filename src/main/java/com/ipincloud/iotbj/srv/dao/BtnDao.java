package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Btn;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(BtnDao) 表数据库访问层
//generate by redcloud,2020-07-08 01:57:14
public interface BtnDao {
    //@param id 主键 
    //@return 实例对象Btn 
    Btn queryById(Long id);
    //@param userId 用户ID 
    //@return 实例Btn列表 
    List<Btn> queryByUserId(long userId);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Btn 
    List<Map> btnRoleBtnMmlist(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数 
    Integer countBtnRoleBtnMmlist(@Param("jsonObj") JSONObject jsonObj);
}
