package com.ipincloud.iotbj.srv.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.Fileitem;
import com.ipincloud.iotbj.srv.dao.FileitemDao;
import com.ipincloud.iotbj.srv.service.FileitemService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Fileitem) 服务实现类
//generate by redcloud,2020-07-07 23:53:41
@Service("FileitemService")
public class FileitemServiceImpl implements FileitemService {
    @Resource
    private FileitemDao fileitemDao;
    //@param id 主键 
    //@return 实例对象Fileitem 
    @Override 
    public Fileitem queryById(Long id){
        this.fileitemDao.queryById(id); 
    } 
    //已处理，参看统一接口/hyupload

}
