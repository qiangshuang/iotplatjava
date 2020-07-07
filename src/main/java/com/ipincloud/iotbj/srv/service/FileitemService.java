package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Fileitem;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj) 服务接口
//generate by redcloud,2020-07-07 23:53:41
public interface FileitemService {
    //@param id 主键 
    //@return 实例对象Fileitem 
    Fileitem queryById(Long id);
    //已处理，参看统一接口/hyupload
}
