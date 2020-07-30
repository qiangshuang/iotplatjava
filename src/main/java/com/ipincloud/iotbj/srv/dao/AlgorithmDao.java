package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Algorithm;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(AlgorithmDao)算法 表数据库访问层
//generate by redcloud,2020-07-24 19:59:20
public interface AlgorithmDao {
    //@param id 主键 
    //@return 实例对象Algorithm 
    Algorithm queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> algorithmList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countAlgorithmList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Algorithm 
    int addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Algorithm 
    int updateInst(@Param("jsonObj") JSONObject jsonObj);
    //设备相关接口，参看api接口/algorithmopen
    //设备相关接口，参看api接口/algorithmclose
    //设备相关接口，参看api接口/algorithmrestart
    //@param jsonObj 过滤条件等 
    //@return 影响记录数 
    Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);
}
