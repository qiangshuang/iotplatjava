package com.ipincloud.iotbj.srv.dao;

import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.Org;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

//(OrgDao) 表数据库访问层
//generate by redcloud,2020-07-15 18:51:58
public interface OrgDao {
    //@param id 主键
    //@return 实例对象Org
    Org queryById(Long id);

    //@param jsonObj 过滤条件等
    //@return 实例对象Org
    List<Map> queryTreeHp(@Param("jsonObj") JSONObject jsonObj);

    //@param jsonObj 过滤条件等
    //@return 实例对象Org
    List<Map> queryTreeNp(@Param("jsonObj") JSONObject jsonObj);

    //@param jsonObj 过滤条件等
    //@return 实例对象Org
    int addInst(@Param("jsonObj") JSONObject jsonObj);

    //@param jsonObj 过滤条件等
    //@return 实例对象Org
    int updateInst(@Param("jsonObj") JSONObject jsonObj);

    //@param jsonObj 过滤条件等
    //@return 实例对象Org
    List<Map> orgQuery(@Param("jsonObj") JSONObject jsonObj);

    //@param jsonObj 过滤条件等
    //@return 实例对象
    List<Map> orgList(@Param("jsonObj") JSONObject jsonObj);

    //@param jsonObj 过滤条件等
    //@return 总条数list
    Integer countOrgList(@Param("jsonObj") JSONObject jsonObj);

    //@param jsonObj 过滤条件等
    //@return 影响记录数
    Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);

    List<JSONObject> listDeleteOrg(@Param("jsonObj") JSONObject jsonObj);

    List<String> queryByIds(@Param("ids") List<Long> ids);

    Org queryByName(String name);

    Org queryByIndexCode(String orgIndexCode);

    int deleteOrgPerson(@Param("deleteIds") List<String> deleteIds);

    int deleteUserPerson(@Param("deleteIds") List<String> deleteIds);
}
