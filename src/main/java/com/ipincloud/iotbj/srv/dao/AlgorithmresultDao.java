package com.ipincloud.iotbj.srv.dao;

import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.Algorithmresult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//(AlgorithmresultDao)算法结果 表数据库访问层
//generate by redcloud,2020-07-23 11:43:17
public interface AlgorithmresultDao {
    //@param id 主键
    //@return 实例对象Algorithmresult
    Algorithmresult queryById(Long id);

    //@param jsonObj 过滤条件等
    //@return 实例对象Algorithmresult
    int addInst(@Param("jsonObj") JSONObject jsonObj);

    //@param jsonObj 过滤条件等
    //@return 实例对象Algorithmresult
    int updateInst(@Param("jsonObj") JSONObject jsonObj);

    JSONObject queryAlgorithm(String accesscode);

    JSONObject queryCamera(String camera_id);

    int addInstAlgorithmalarm(@Param("jsonObj") JSONObject jsonObj);

    List<String> queryAccesscode();

    JSONObject queryAlgorithmByCidAndAid(Long id, Long id1);
}
